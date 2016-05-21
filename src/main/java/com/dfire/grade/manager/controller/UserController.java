package com.dfire.grade.manager.controller;

import com.dfire.grade.manager.Contants;
import com.dfire.grade.manager.bean.SignBean;
import com.dfire.grade.manager.bean.Teacher;
import com.dfire.grade.manager.logger.LoggerFactory;
import com.dfire.grade.manager.logger.LoggerMarker;
import com.dfire.grade.manager.service.*;
import com.dfire.grade.manager.utils.MessageDigestUtil;
import com.dfire.grade.manager.utils.RedisUtil;
import com.dfire.grade.manager.utils.SequenceUtil;
import com.dfire.grade.manager.utils.SmsUtil;
import com.dfire.grade.manager.vo.ClassVo2;
import com.dfire.grade.manager.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * User:huangtao
 * Date:2016-03-25
 * description：
 */
@Controller
@RequestMapping("user")
public class UserController extends BaseController {
    @Autowired
    private ITeacherService teacherService;
    @Autowired
    private IStudentService studentService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private IStudentClassService studentClassService;
    @Autowired
    private IClassService classService;
    @Autowired
    private IAnswerService answerService;
    @Autowired
    private IJobService jobService;
    @Autowired
    private SmsUtil smsUtil;
    private String TEACHER_MESSAGE = "\n+课程码：%s。课程名： %s.\n学生：姓名： %s,学号：%s 申请加入课程";
    //    private final String METHOD_GET = "get";


    /**
     * 用户注册
     *
     * @param name
     * @param mobile
     * @param email
     * @param school
     * @param type   1 standby student ,2 standby teacher
     * @return
     */
    @RequestMapping(value = "/signup", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseStatus(HttpStatus.OK)
    public String signUp(HttpServletRequest request, Model model, String name, String mobile, String email, String school, String passWord, Integer sex, Integer type) throws Exception {
        if (request.getMethod().endsWith(Contants.Http.METHOD_POST)) {
            JsonResult result = null;
            if (type == 1) {
                result = studentService.insertStudent(name, school, passWord, mobile, email, sex);
            } else {
                if (type == 2) {
                    result = teacherService.insertTeacher(name, school, passWord, mobile, email, sex);
                } else {
                    model.addAttribute("message", "角色错误！");
                }
            }
            if (null != result && result.isSuccess()) {
                SignBean signBean = (SignBean) result.getData();
                Map<String, Object> map = new HashMap<>();
                map.put("signBean", signBean);
                map.put("message", "用户注册成功！");
                LoggerFactory.USER_FACTORY.info(LoggerMarker.USER_SIGN, SequenceUtil.mapToJson(map));
                return "login";
            }
            model.addAttribute("message", result.getMessage());
        }
        return "signup";
    }

    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseStatus(HttpStatus.OK)
    public String signIn(HttpServletRequest request, Model model, String mobile, String passWord, Integer type) throws Exception {

        if (request.getMethod().equals(Contants.Http.METHOD_POST)) {
            JsonResult result = null;
            if (type == 1) {
                result = studentService.queryRoleByMobile(mobile);
            } else {
                if (type == 2) {
                    result = teacherService.queryRoleByMobile(mobile);
                } else {
                    model.addAttribute("message", "角色错误！");
                }
            }
            if (result.isSuccess()) {
                if (null != result.getData()) {
                    SignBean signBean = (SignBean) result.getData();
                    Map<String, Object> map = new HashMap<>();
                    map.put("signBean", signBean);
                    if (MessageDigestUtil.getStrCode(passWord).equals(signBean.getPassWord())) {
                        signBean.setSign(true);
                        String keyMobile = null;
                        String keyId = null;
                        String key = Contants.STUDENT_KEY;
                        if (type == 1) {
                            keyId = Contants.RedisContent.STUDENT_SIGN_CACHE_BY_ID + signBean.getId();
                            keyMobile = Contants.RedisContent.STUDENT_SIGN_CACHE_BY_MOBILE + mobile;
                        } else {
                            key = Contants.TEACHER_KEY;
                            keyMobile = Contants.RedisContent.TEACHER_SIGN_CACHE_BY_MOBILE + mobile;
                            keyId = Contants.RedisContent.TEACHER_SIGN_CACHE_BY_ID + signBean.getId();
                        }
                        redisUtil.setValuePre(keyId, signBean, Contants.RedisContent.USERINFO_EXPIRE_TIME, Contants.RedisContent.MINUTES_UNIT);
                        redisUtil.setValuePre(keyMobile, signBean, Contants.RedisContent.USERINFO_EXPIRE_TIME, Contants.RedisContent.MINUTES_UNIT);
                        map.put("message", "用户登录成功！");
                        request.getSession().setAttribute(key, signBean);
                        request.getSession().setAttribute(Contants.USER_KEY, signBean);
                        LoggerFactory.USER_FACTORY.info(LoggerMarker.USER_SIGN, SequenceUtil.mapToJson(map));
                        model.addAttribute("type", type);
                        return "index";
                    } else {
                        map.put("message", "用户登录失败，密码错误！");
                        LoggerFactory.USER_FACTORY.error(LoggerMarker.USER_SIGN, SequenceUtil.mapToJson(map));
                        model.addAttribute("message", "密码错误！");
                    }
                }
            } else {
                model.addAttribute("message", "请求失败！");
            }
        }
        return "login";
    }

    @RequestMapping(value = "forget_password", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String forgetPassword() {
        return "forgetPassWord";
    }

    @RequestMapping(value = "verify/login", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public String verifyAndLogin(HttpServletRequest request, Model model, String mobile, String code) throws Exception {
        String cacheCode = (String) redisUtil.getValue(Contants.RedisContent.VERIFY_CODE_PREFIX + mobile, String.class);
        if (null == code || StringUtils.isEmpty(code)) {
            model.addAttribute("message", "验证码不能为空");
            return "forgetPassWord";
        }
        if (null == cacheCode) {
            model.addAttribute("message", "验证码过期");
            return "forgetPassWord";
        }
        if (!cacheCode.equals(code)) {
            model.addAttribute("message", "错误的验证码");
            return "forgetPassWord";
        }
        redisUtil.del(Contants.RedisContent.VERIFY_CODE_PREFIX + mobile);
        JsonResult result = null;
        int type = 2;
        String key = Contants.STUDENT_KEY;
        result = studentService.queryRoleByMobile(mobile);
        if (null == result || !result.isSuccess() || null == result.getData()) {
            type = 1;
            key = Contants.TEACHER_KEY;
            result = teacherService.queryRoleByMobile(mobile);
        }
        if (result.isSuccess()) {
            if (null != result.getData()) {
                SignBean signBean = (SignBean) result.getData();
                Map<String, Object> map = new HashMap<>();
                map.put("signBean", signBean);
                String keyMobile = null;
                String keyId = null;
                if (type == 1) {
                    keyId = Contants.RedisContent.STUDENT_SIGN_CACHE_BY_ID + signBean.getId();
                    keyMobile = Contants.RedisContent.STUDENT_SIGN_CACHE_BY_MOBILE + mobile;
                } else {
                    keyMobile = Contants.RedisContent.TEACHER_SIGN_CACHE_BY_MOBILE + mobile;
                    keyId = Contants.RedisContent.TEACHER_SIGN_CACHE_BY_ID + signBean.getId();
                }
                redisUtil.setValuePre(keyId, signBean, Contants.RedisContent.USERINFO_EXPIRE_TIME, Contants.RedisContent.MINUTES_UNIT);
                redisUtil.setValuePre(keyMobile, signBean, Contants.RedisContent.USERINFO_EXPIRE_TIME, Contants.RedisContent.MINUTES_UNIT);
                map.put("message", "用户登录成功！");
                request.getSession().setAttribute(key, signBean);
                LoggerFactory.USER_FACTORY.info(LoggerMarker.USER_SIGN, SequenceUtil.mapToJson(map));
                return "index";
            }
        }
        model.addAttribute("message", "请求失败！");
        return "forgetPassWord";
    }

    @RequestMapping(value = "/main", method = {RequestMethod.POST})
    @ResponseStatus(HttpStatus.OK)
    public String forMainContainer(HttpServletRequest request, Model model) throws Exception {
        return "main";
    }

    @RequestMapping("/login_out")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public JsonResult loginOut(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession(false);
        if (null != session) {
            session.removeAttribute(Contants.STUDENT_KEY);
            session.removeAttribute(Contants.TEACHER_KEY);
            session.removeAttribute(Contants.USER_KEY);
        }
        return JsonResult.success();
    }

    @RequestMapping(value = "/join_class", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public JsonResult joinClass(String mobile, String code, String classNo, String studentNo, String studentName) throws Exception {
        if (!StringUtils.isEmpty(mobile) && !StringUtils.isEmpty(code) && !StringUtils.isEmpty(classNo)
                && !StringUtils.isEmpty(studentNo) && !StringUtils.isEmpty(studentName)) {
            String cacheCode = (String) redisUtil.getValue(Contants.RedisContent.VERIFY_CODE_PREFIX + mobile, String.class);
            if (null == cacheCode) {
                return JsonResult.failedInstance("验证码过期！");
            }
            if (!cacheCode.equals(code)) {
                return JsonResult.failedInstance("验证码错误！");
            }
            String classId = (String) redisUtil.getValue(Contants.RedisContent.CLASS_CODE_PREFIX + classNo, String.class);
            if (!StringUtils.isEmpty(classId)) {
                JsonResult claRe = classService.selectClassById(classId);
                if (claRe.isSuccess() && null != claRe.getData()) {
                    JsonResult joinRe = studentClassService.selectIfJoinedById(mobile, classId);
                    if (joinRe.isSuccess() && null == joinRe.getData()) {
                        ClassVo2 classVo = (ClassVo2) claRe.getData();
                        String teacherId = classVo.getTeacherId();
                        String teacherName = classVo.getTeacherName();
                        String className = classVo.getName();
                        JsonResult shipRe = studentClassService.createRelationship(teacherId, null, classId, mobile, studentNo, studentName, classNo, teacherName);
                        if (shipRe.isSuccess()) {
                            JsonResult teaRe = teacherService.queryRoleById(teacherId);
                            //短信提醒
                            if (teaRe.isSuccess() && null != teaRe.getData()) {
                                Teacher teacher = (Teacher) teaRe.getData();
                                String message = String.format(TEACHER_MESSAGE, classNo, className, studentName, studentNo);
                                try {
                                    smsUtil.sendSMS(teacher.getMobile(), message);
                                } catch (Exception e) {
                                }
                            }
                            redisUtil.del(Contants.RedisContent.VERIFY_CODE_PREFIX + mobile);
                            return JsonResult.jsonSuccessMes("申请成功");
                        } else {
                            return JsonResult.failedInstance(shipRe.getMessage());
                        }
                    } else {
                        return JsonResult.failedInstance("已申请加入此课程，请联系教师解决！");
                    }
                }
            }
            return JsonResult.failedInstance("无此课程！");
        } else {
            return JsonResult.failedInstance("参数为空！");
        }
    }

    @RequestMapping(value = "/join", method = {RequestMethod.GET})
    @ResponseStatus(HttpStatus.OK)
    public String joinClassForGet() throws Exception {
        return "class/join";
    }

    @RequestMapping(value = "/return", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseStatus(HttpStatus.OK)
    public String returnHome(HttpServletRequest request, Model model) throws Exception {

        SignBean signBean = (SignBean) request.getSession().getAttribute(Contants.STUDENT_KEY);
        model.addAttribute("type", 1);
        String message = "";
        if (null != signBean) {
            String student = signBean.getId();
            String id = (String) request.getSession().getAttribute("id");
            String fileName = (String) request.getSession().getAttribute("fileName");
            if (!StringUtils.isEmpty(id) && !StringUtils.isEmpty(fileName) && !fileName.equals("null")) {
                JsonResult jsonResult = answerService.createAnswer(student, id, fileName);
                message = jsonResult.getMessage();
                request.getSession().removeAttribute("id");
                request.getSession().removeAttribute("fileName");
            } else {
                message = "提交失败！";
            }
        } else {
            model.addAttribute("type", 2);
        }
        model.addAttribute("message", message);
        return "index";
//        String studentId = signBean.getId();
//        JsonResult result = studentService.queryRoleById(id);
//
//        if (result.isSuccess() && null != result.getData()) {
//            Map<String, Object> map = new HashMap<>();
//            if (!StringUtils.isEmpty(studentId)) {
//                map.put("studentId", studentId);
//            }
//            int pageSize = 1000;
//            map.put("pageSize", pageSize);
//            int pageIndex = 1;
//            int index = (pageIndex - 1) * 10 - 1;
//            map.put("index", index == -1 ? 0 : index);
//            JsonResult rs = jobService.selectJob(map);
//            if (rs.isSuccess()) {
//                if (null != rs.getData() && !CollectionUtils.isEmpty((Collection<?>) rs.getData())) {
//                    List<JobVo> jobVos = (List<JobVo>) rs.getData();
//                    if (!StringUtils.isEmpty(studentId)) {
//                        Answer answer = new Answer();
//                        answer.setStudentId(studentId);
//                        JsonResult ansRe = answerService.selectAnswerByCondition(answer);
//                        if (ansRe.isSuccess() && null != ansRe.getData()) {
//                            List<AnswerVo> answerVoList = (List<AnswerVo>) ansRe.getData();
//                            for (int i = 0; i < jobVos.size(); i++) {
//                                for (AnswerVo answerVo : answerVoList) {
//                                    if (jobVos.get(i).getJobId().equals(answerVo.getJobId())) {
//                                        if (answerVo.isAnswered()) {
//                                            jobVos.get(i).setAnswered(true);
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                    model.addAttribute("jobList", jobVos);
//                } else {
//                    message = rs.getMessage();
//                }
//            } else {
//                message = Contants.Message.ERROR_REQUEST;
//            }
//        }
//        model.addAttribute("message", message);
//        return "job/list";
    }
}
