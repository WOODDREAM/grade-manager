package com.dfire.grade.manager.controller;

import com.dfire.grade.manager.Contants;
import com.dfire.grade.manager.bean.SignBean;
import com.dfire.grade.manager.service.IStudentService;
import com.dfire.grade.manager.service.ITeacherService;
import com.dfire.grade.manager.utils.MessageDigestUtil;
import com.dfire.grade.manager.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * User:huangtao
 * Date:2016-03-25
 * description：
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private ITeacherService teacherService;
    @Autowired
    private IStudentService studentService;

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
    @RequestMapping(value = "/sign_up", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public JsonResult signUp(@RequestParam(value = "name", required = true) String name,
                             @RequestParam(value = "mobile", required = true) String mobile,
                             @RequestParam(value = "email", required = false, defaultValue = "") String email,
                             @RequestParam(value = "school", required = true) String school,
                             @RequestParam(value = "pass_word", required = true) String passWord,
                             @RequestParam(value = "sex", required = true) Integer sex,
                             @RequestParam(value = "type", required = true) Integer type) throws Exception {
        if (type == 1) {
            return studentService.insertStudent(name, school, passWord, mobile, email, sex);
        }
        if (type == 2) {
            return teacherService.insertTeacher(name, school, email, mobile, passWord, sex);
        }
        return JsonResult.failedInstance(Contants.Message.ERROR_NO_USER_TYPE);
    }

    @RequestMapping(value = "/sign_in", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public JsonResult signIn(@RequestParam(value = "mobile", required = true) String mobile,
                             @RequestParam(value = "pass_word", required = true) String passWord,
                             @RequestParam(value = "type", required = true) Integer type) throws Exception {
        JsonResult result = null;
        if (type == 1) {
            result = studentService.queryRoleByMobile(mobile);
        } else {
            if (type == 2) {
                result = teacherService.queryRoleByMobile(mobile);
            } else {
                return JsonResult.failedInstance(Contants.Message.ERROR_NO_USER_TYPE);
            }
        }
        if (result.isSuccess()) {
            if (null != result.getData()) {
                SignBean signBean = (SignBean) result.getData();
                if (MessageDigestUtil.getStrCode(passWord).equals(signBean.getPassWord())) {
                    return JsonResult.jsonSuccessData(signBean);
                } else
                    return JsonResult.newInstance2(String.valueOf(Contants.ErrorCode.ERROR_1006), Contants.Message.ERROR_PASS_WORD);
            }
            return JsonResult.jsonSuccessMes(Contants.Message.ERROR_PLEASE_SIGN_UP);
        } else {
            return JsonResult.failedInstance(Contants.Message.ERROR_REQUEST);
        }
    }
}
