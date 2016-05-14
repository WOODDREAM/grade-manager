package com.dfire.grade.manager.controller;

import com.dfire.grade.manager.Contants;
import com.dfire.grade.manager.bean.SignBean;
import com.dfire.grade.manager.logger.LoggerFactory;
import com.dfire.grade.manager.logger.LoggerMarker;
import com.dfire.grade.manager.service.IStudentService;
import com.dfire.grade.manager.service.ITeacherService;
import com.dfire.grade.manager.utils.MessageDigestUtil;
import com.dfire.grade.manager.utils.RedisUtil;
import com.dfire.grade.manager.utils.SequenceUtil;
import com.dfire.grade.manager.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
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
                return "index";
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
                        request.getSession().setAttribute(Contants.USER_KEY, signBean);
                        LoggerFactory.USER_FACTORY.info(LoggerMarker.USER_SIGN, SequenceUtil.mapToJson(map));

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
        result = studentService.queryRoleByMobile(mobile);
        if (null == result || !result.isSuccess() || null == result.getData()) {
            type = 1;
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
                request.getSession().setAttribute(Contants.USER_KEY, signBean);
                LoggerFactory.USER_FACTORY.info(LoggerMarker.USER_SIGN, SequenceUtil.mapToJson(map));
                return "index";
            }
        }
        model.addAttribute("message", "请求失败！");
        return "forgetPassWord";
    }
}
