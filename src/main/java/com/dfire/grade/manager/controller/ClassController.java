package com.dfire.grade.manager.controller;

import com.dfire.grade.manager.Contants;
import com.dfire.grade.manager.bean.ClassStart;
import com.dfire.grade.manager.bean.SignBean;
import com.dfire.grade.manager.bean.StudentClass;
import com.dfire.grade.manager.bean.Teacher;
import com.dfire.grade.manager.service.IClassService;
import com.dfire.grade.manager.service.IClassStartService;
import com.dfire.grade.manager.service.IStudentClassService;
import com.dfire.grade.manager.service.ITeacherService;
import com.dfire.grade.manager.utils.DateUtil;
import com.dfire.grade.manager.utils.RedisUtil;
import com.dfire.grade.manager.utils.SequenceUtil;
import com.dfire.grade.manager.utils.SmsUtil;
import com.dfire.grade.manager.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * User:huangtao
 * Date:2016-03-23
 * description：
 */
@RequestMapping("/class")
@Controller
public class ClassController extends BaseController {
    @Autowired
    private IClassService classService;
    @Autowired
    private ITeacherService teacherService;
    @Autowired
    private IClassStartService classStartService;
    @Autowired
    private IStudentClassService studentClassService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private SmsUtil smsUtil;
    private String CONTENT = "课程码：%s，课程名： %s。此码用于学生加入课程\n" + "课程码有效时间为两周，如果无效，请重新开课";

    @RequestMapping(value = "/create", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseStatus(HttpStatus.OK)
    public String createClass(HttpServletRequest request, Model model,
                              @RequestParam(value = "frequencyUnit", required = false) String frequencyUnit,
                              @RequestParam(value = "name", required = false) String name,
                              @RequestParam(value = "period", required = false) Double period,
                              @RequestParam(value = "credit", required = false) Double credit,
                              @RequestParam(value = "startTime", required = false) String startTime,
                              @RequestParam(value = "endTime", required = false) String endTime,
                              @RequestParam(value = "schoolTimes", required = false) String schoolTimes) throws Exception {
        if (request.getMethod().equals(Contants.Http.METHOD_POST)) {
            SignBean signBean = (SignBean) request.getSession().getAttribute(Contants.TEACHER_KEY);
            String teacherId = signBean.getId();
            JsonResult teaRe = teacherService.queryRoleById(teacherId);
            if (teaRe.isSuccess() && null != teaRe.getData()) {
                List<ClassIncludeSchoolTime> classIncludeSchoolTimes = new ArrayList<>();
                List<Schedule> sch = SequenceUtil.stringToSchedule(schoolTimes);
                if (!CollectionUtils.isEmpty(sch)) {
                    ClassIncludeSchoolTime classIncludeSchoolTime = new ClassIncludeSchoolTime();
                    classIncludeSchoolTime.setCredit(credit);
                    classIncludeSchoolTime.setName(name);
                    classIncludeSchoolTime.setPeriod(period);
                    classIncludeSchoolTime.setTeacherId(teacherId);
                    classIncludeSchoolTime.setSchoolTimes(sch);
                    classIncludeSchoolTime.setFrequency(sch.size());
                    classIncludeSchoolTime.setFrequencyUnit(frequencyUnit);
                    classIncludeSchoolTime.setEndTime(DateUtil.parseDate(endTime, DateUtil.DEFAULT_MOUTH_DAY_YEAR));
                    classIncludeSchoolTime.setStartTime(DateUtil.parseDate(startTime, DateUtil.DEFAULT_MOUTH_DAY_YEAR));
                    classIncludeSchoolTimes.add(classIncludeSchoolTime);
                    JsonResult result = classService.insertClass(classIncludeSchoolTimes);
                    if (result.isSuccess()) {
                        JsonResult classes = classService.selectAllClassByTeacherIdAndPage(teacherId, 0, 1000, null, null);
                        if (classes.isSuccess() && null != classes.getData()) {
                            //所有课程
                            List<ClassVo2> classVos = (List<ClassVo2>) classes.getData();
                            JsonResult jsonResult = classStartService.selectByTeacher(teacherId);
                            if (jsonResult.isSuccess() && null != jsonResult.getData()) {
                                //开课中的课程
                                List<ClassStart> classStarts = (List<ClassStart>) jsonResult.getData();
                                for (int i = 0; i < classVos.size(); i++) {
                                    for (ClassStart classStart : classStarts) {
                                        if (classStart.getClassId().equals(classVos.get(i).getClassId())) {
                                            classVos.get(i).setState(Contants.ClassState.STARTING);
                                        }
                                    }
                                }
                            }
                            model.addAttribute("classList", classVos);
                        } else {
                            model.addAttribute("message", "没有查出数据");
                        }
                        model.addAttribute("roleType", 2);
                        return "class/class_list";
                    } else {
                        model.addAttribute("message", "创建失败！");
                    }
                } else {
                    model.addAttribute("message", "没有上课时间，放弃创建");
                }
            } else {
                model.addAttribute("message", "没有权限！");
            }
        }
        return "class/create_class";
    }

    @RequestMapping(value = "/detail", method = {RequestMethod.POST})
    @ResponseStatus(HttpStatus.OK)
    public String getClassIncludeDetail(Model model, @RequestParam(value = "classId", required = true) String classId) throws Exception {
        JsonResult classes = classService.selectClassIncludeDetailById(classId);
        if (classes.isSuccess() && null != classes.getData()) {
            model.addAttribute("classDetail", classes.getData());
        } else {
            model.addAttribute("message", "请求失败");
        }
        return "class/class_detail";
    }

    @RequestMapping(value = "/teacher", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseStatus(HttpStatus.OK)
    public String getTeacherClass(HttpServletRequest request, Model model,
                                  @RequestParam(value = "startTime", required = false) Date startTime,
                                  @RequestParam(value = "endTime", required = false) Date endTime,
                                  @RequestParam(value = "pageIndex", required = false) Integer pageIndex,
                                  @RequestParam(value = "pageSize", required = false) Integer pageSize) throws Exception {
        if (null == pageIndex) {
            pageIndex = 1;
        }
        if (null == pageSize) {
            pageSize = 1000;
        }
        int index = ((pageIndex - 1) * 10) - 1;
        if (-1 == index) {
            index = 0;
        }
        SignBean signBean = (SignBean) request.getSession().getAttribute(Contants.TEACHER_KEY);
        String teacherId = signBean.getId();
        JsonResult classes = classService.selectAllClassByTeacherIdAndPage(teacherId, index, pageSize, startTime, endTime);
        if (classes.isSuccess() && null != classes.getData()) {
            //所有课程
            List<ClassVo2> classVos = (List<ClassVo2>) classes.getData();
            JsonResult jsonResult = classStartService.selectByTeacher(teacherId);
            if (jsonResult.isSuccess() && null != jsonResult.getData()) {
                //开课中的课程
                List<ClassStart> classStarts = (List<ClassStart>) jsonResult.getData();
                for (int i = 0; i < classVos.size(); i++) {
                    for (ClassStart classStart : classStarts) {
                        if (classStart.getClassId().equals(classVos.get(i).getClassId())) {
                            classVos.get(i).setState(Contants.ClassState.STARTING);
                        }
                    }
                }
            }
            model.addAttribute("classList", classVos);
        } else {
            model.addAttribute("message", "没有查出数据");
        }
        model.addAttribute("roleType", 2);
        return "class/class_list";
    }

    @RequestMapping(value = "/student", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseStatus(HttpStatus.OK)
    public String getStudentClass(HttpServletRequest request, Model model,
                                  @RequestParam(value = "startTime", required = false) Date startTime,
                                  @RequestParam(value = "endTime", required = false) Date endTime,
                                  @RequestParam(value = "pageIndex", required = false) Integer pageIndex,
                                  @RequestParam(value = "pageSize", required = false) Integer pageSize) throws Exception {
        if (null == pageIndex) {
            pageIndex = 1;
        }
        if (null == pageSize) {
            pageSize = 1000;
        }
        SignBean signBean = (SignBean) request.getSession().getAttribute(Contants.STUDENT_KEY);
        String student = signBean.getId();
        int index = ((pageIndex - 1) * 10) - 1;
        if (-1 == index) {
            index = 0;
        }
        JsonResult classes = studentClassService.selectRelationship(null, null, student, index, pageSize, startTime, endTime);
        if (classes.isSuccess() && null != classes.getData()) {
            //学生所有的课程
            RelationshipVo relationshipVo = (RelationshipVo) classes.getData();
            List<Reliation> agreeClass = relationshipVo.getAgreeClass();
            List<Reliation> notAgreeClass = relationshipVo.getNotAgreeClass();
            List<String> classIds = new ArrayList<>();
            if (!CollectionUtils.isEmpty(agreeClass)) {
                for (Reliation reliation : agreeClass) {
                    classIds.add(reliation.getClassId());
                }
            }
            if (!CollectionUtils.isEmpty(notAgreeClass)) {
                for (Reliation reliation : notAgreeClass) {
                    classIds.add(reliation.getClassId());
                }
            }
            List<ClassStart> classStartList = null;
            if (!CollectionUtils.isEmpty(classIds)) {
                //开课中的课程
                JsonResult result = classStartService.selectClassBatch(classIds);
                if (result.isSuccess() && null != result.getData()) {
                    classStartList = (List<ClassStart>) result.getData();
                }
            }
            //
            JsonResult result = classService.selectClassBatch(classIds);
            if (result.isSuccess() && null != result.getData()) {
                //所有课程
                List<ClassVo2> classVo2List = (List<ClassVo2>) result.getData();
                for (int i = 0; i < classVo2List.size(); i++) {
                    if (!CollectionUtils.isEmpty(agreeClass)) {
                        for (Reliation r : agreeClass) {
                            if (classVo2List.get(i).getClassId().equals(r.getClassId())) {
                                classVo2List.get(i).setAgree(r.isAgree());
                            }
                        }
                    }
                    if (!CollectionUtils.isEmpty(notAgreeClass)) {
                        for (Reliation r : notAgreeClass) {
                            if (classVo2List.get(i).getClassId().equals(r.getClassId())) {
                                classVo2List.get(i).setAgree(r.isAgree());
                            }
                        }
                    }
                    if (!CollectionUtils.isEmpty(classStartList)) {
                        for (ClassStart r : classStartList) {
                            if (classVo2List.get(i).getClassId().equals(r.getClassId())) {
                                classVo2List.get(i).setState(Contants.ClassState.STARTING);
                            }
                        }
                    }
                }
                model.addAttribute("classList", classVo2List);
            } else {
                model.addAttribute("message", "您没有加入任何课程");
            }
        } else {
            model.addAttribute("message", "您没有加入任何课程");
        }
        model.addAttribute("roleType", 1);
        return "class/class_list";
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public JsonResult deleteClass(HttpServletRequest request, Model model, @RequestParam(value = "classId", required = true) String classId) throws Exception {
        SignBean signBean = (SignBean) request.getSession().getAttribute(Contants.TEACHER_KEY);
        String teacherId = signBean.getId();
        JsonResult teaRe = teacherService.queryRoleById(teacherId);
        if (teaRe.isSuccess() && null != teaRe.getData()) {
            classService.deleteClassByClassId(classId);
            return JsonResult.jsonSuccessMes(Contants.Message.SUCCESS_REQUEST);
        }
        return JsonResult.failedInstance(Contants.Message.NOT_PERMISSION);
    }

    @RequestMapping(value = "/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseStatus(HttpStatus.OK)
    public String updateClass(HttpServletRequest request, Model model,
                              @RequestParam(value = "classId", required = true) String classId,
                              @RequestParam(value = "name", required = false) String name,
                              @RequestParam(value = "period", required = false) Double period,
                              @RequestParam(value = "credit", required = false) Double credit,
                              @RequestParam(value = "startTime", required = false) String startTime,
                              @RequestParam(value = "endTime", required = false) String endTime,
                              @RequestParam(value = "schoolTimes", required = false) String schoolTimes) throws Exception {
        if (!StringUtils.isEmpty(classId)) {
            if (request.getMethod().equals(Contants.Http.METHOD_POST)) {
                SignBean signBean = (SignBean) request.getSession().getAttribute(Contants.TEACHER_KEY);
                String teacherId = signBean.getId();
                JsonResult teaRe = teacherService.queryRoleById(teacherId);
                if (teaRe.isSuccess() && null != teaRe.getData()) {
                    List<ClassIncludeSchoolTime> classIncludeSchoolTimes = new ArrayList<>();
                    List<Schedule> sch = SequenceUtil.stringToSchedule(schoolTimes);
                    if (!CollectionUtils.isEmpty(sch) && !StringUtils.isEmpty(classId)) {
                        ClassIncludeSchoolTime classIncludeSchoolTime = new ClassIncludeSchoolTime();
                        classIncludeSchoolTime.setCredit(credit);
                        classIncludeSchoolTime.setName(name);
                        classIncludeSchoolTime.setPeriod(period);
                        classIncludeSchoolTime.setTeacherId(teacherId);
                        classIncludeSchoolTime.setSchoolTimes(sch);
                        classIncludeSchoolTime.setClassId(classId);
                        classIncludeSchoolTime.setFrequency(sch.size());
                        if (endTime.contains("/")) {
                            classIncludeSchoolTime.setEndTime(DateUtil.parseDate(endTime, DateUtil.DEFAULT_MOUTH_DAY_YEAR));
                        } else {
                            classIncludeSchoolTime.setEndTime(DateUtil.parseDate(endTime, DateUtil.DEFAULT_DATE_FORMAT));
                        }
                        if (startTime.contains("/")) {
                            classIncludeSchoolTime.setStartTime(DateUtil.parseDate(startTime, DateUtil.DEFAULT_MOUTH_DAY_YEAR));
                        } else {
                            classIncludeSchoolTime.setStartTime(DateUtil.parseDate(startTime, DateUtil.DEFAULT_DATE_FORMAT));
                        }
                        classIncludeSchoolTimes.add(classIncludeSchoolTime);
                        JsonResult result = classService.upDateClassByClassId(classIncludeSchoolTimes);
                        if (result.isSuccess()) {
                            JsonResult classRe = classService.selectAllClassByTeacherIdAndPage(teacherId, 0, 10, null, null);
                            if (classRe.isSuccess() && null != classRe.getData()) {
                                model.addAttribute("classList", classRe.getData());
                                model.addAttribute("roleType", 2);
                                return "class/class_list";
                            }
                            model.addAttribute("message", "创建失败！");
                        }
                    } else {
                        model.addAttribute("message", "没有上课时间，放弃更新！");
                    }
                } else {
                    model.addAttribute("message", "没有权限！");
                }
            }
            JsonResult result = classService.selectClassIncludeDetailById(classId);
            if (result.isSuccess() && null != result.getData()) {
                model.addAttribute("classDetail", result.getData());
            }
        } else {
            model.addAttribute("message", "参数错误 classId不能为空！");
        }
        return "class/update";
    }

    @RequestMapping(value = "/start", method = {RequestMethod.POST})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public JsonResult startClass(HttpServletRequest request, @RequestParam(value = "classId", required = true) String classId) throws Exception {
        if (!StringUtils.isEmpty(classId)) {
            SignBean signBean = (SignBean) request.getSession().getAttribute(Contants.TEACHER_KEY);
            String teacherId = signBean.getId();
            JsonResult teaRe = teacherService.queryRoleById(teacherId);
            if (teaRe.isSuccess() && null != teaRe.getData()) {
                Teacher teacher = (Teacher) teaRe.getData();
                JsonResult result = classService.selectClassById(classId);
                if (result.isSuccess() && null != result.getData()) {
                    Random rand = new Random();
                    String classNo = String.valueOf(rand.nextInt(9000) + 1000);
                    String code = (String) redisUtil.getValue(Contants.RedisContent.CLASS_CODE_PREFIX + classNo, String.class);
                    boolean flag = true;
                    while (flag) {
                        if (classNo.equals(code)) {
                            classNo = String.valueOf(rand.nextInt(9000) + 1000);
                        } else {
                            flag = false;
                        }
                    }
                    ClassVo2 classVo = (ClassVo2) result.getData();
                    String className = classVo.getName();
                    classService.startClass(classId, classNo, teacherId, className);
                    redisUtil.setValuePre(Contants.RedisContent.CLASS_CODE_PREFIX + classNo, classId, Contants.RedisContent.CLASS_CODE_EXPIRE_TIME, Contants.RedisContent.HOURS_UNIT);
                    String message = String.format(CONTENT, classNo, className);
                    try {
                        smsUtil.sendSMS(teacher.getMobile(), message);
                    } catch (Exception e) {
                    }
                    return JsonResult.jsonSuccessMes("开课成功！课程码为：" + classNo + "详情见短信\n短信可能会延迟发送");
                } else {
                    return JsonResult.failedInstance("没有此课程");
                }
            } else {
                return JsonResult.failedInstance("没有权限");
            }
        } else {
            return JsonResult.failedInstance("classId为空");
        }
    }

    /**
     * 结束选课
     *
     * @param request
     * @param classId
     * @param classNo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/end", method = {RequestMethod.POST})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public JsonResult endClass(HttpServletRequest request, @RequestParam(value = "classId", required = true) String classId,
                               @RequestParam(value = "classNo", required = false) String classNo) throws Exception {
        if (!StringUtils.isEmpty(classId)) {
            SignBean signBean = (SignBean) request.getSession().getAttribute(Contants.TEACHER_KEY);
            String teacherId = signBean.getId();
            JsonResult teaRe = teacherService.queryRoleById(teacherId);
            if (teaRe.isSuccess() && null != teaRe.getData()) {
                return classStartService.endClass(classId, classNo);
            }
            return JsonResult.failedInstance(Contants.Message.NOT_PERMISSION);
        } else {
            return JsonResult.failedInstance("classId不能为空！");
        }
    }

    @RequestMapping(value = "/make_join", method = {RequestMethod.POST})
    @ResponseStatus(HttpStatus.OK)
    public String makeStudentJoinClass(HttpServletRequest request, Model model,
                                       @RequestParam(value = "classId", required = true) String classId,
                                       @RequestParam(value = "mobile", required = false) String mobile,
                                       @RequestParam(value = "studentNo", required = false) String studentNo,
                                       @RequestParam(value = "studentName", required = false) String studentName) throws Exception {
        if (!StringUtils.isEmpty(classId)) {
            SignBean signBean = (SignBean) request.getSession().getAttribute(Contants.TEACHER_KEY);
            String teacherId = signBean.getId();
            JsonResult teaRe = teacherService.queryRoleById(teacherId);
            if (teaRe.isSuccess() && null != teaRe.getData()) {
                Teacher teacher = (Teacher) teaRe.getData();
                JsonResult classes = classService.selectClassIncludeDetailById(classId);
                if (classes.isSuccess() && null != classes.getData()) {
                    model.addAttribute("classDetail", classes.getData());
                    if (!StringUtils.isEmpty(mobile)) {
                        if (!StringUtils.isEmpty(studentNo) && !StringUtils.isEmpty(studentName)) {
                            JsonResult joinRe = studentClassService.selectIfJoinedById(mobile, classId);
                            if (joinRe.isSuccess() && null == joinRe.getData()) {
                                JsonResult result = studentClassService.createRelationship(teacherId, null, classId, mobile, studentNo, studentName, Contants.Message.STUDENT_JOINED_BY_TEACHER, teacher.getName());
                                if (result.isSuccess()) {
                                    return returnResult(classId, teacherId, model);
                                } else {
                                    model.addAttribute("message", result.getMessage());
                                }
                            } else {
                                StudentClass studentClass = (StudentClass) joinRe.getData();
                                studentClassService.updateAgree(studentClass.getRelationshipId());
                                return returnResult(classId, teacherId, model);
                            }
                        } else {
                            model.addAttribute("message", "请填写完整学生信息！");
                        }
                    }
                } else {
                    model.addAttribute("message", Contants.Message.ERROR_NO_CLASS);
                }
            } else {
                model.addAttribute("message", Contants.Message.NOT_PERMISSION);
            }
        } else {
            model.addAttribute("message", "没有选择课程");
        }
        return "class/make_join";
    }

    private String returnResult(String classId, String teacherId, Model model) throws Exception {
        int index = ((0 - 1) * 10) - 1;
        if (-1 == index) {
            index = 0;
        }
        JsonResult resultS = studentClassService.selectRelationship(classId, teacherId, null, index, 1000, null, null);
        if (resultS.isSuccess() && null != resultS.getData()) {
            RelationshipVo relationshipVo = (RelationshipVo) resultS.getData();
            List<Reliation> agreeClass = relationshipVo.getAgreeClass();
            List<Reliation> notAgreeClass = relationshipVo.getNotAgreeClass();
            model.addAttribute("agreeClass", agreeClass);
            model.addAttribute("notAgreeClass", notAgreeClass);
            model.addAttribute("roleType", 2);
        } else {
            model.addAttribute("message", resultS.getMessage());
        }
        return "ship/list";
    }
}
