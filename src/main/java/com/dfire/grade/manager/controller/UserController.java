package com.dfire.grade.manager.controller;

import com.dfire.grade.manager.service.IStudentService;
import com.dfire.grade.manager.service.ITeacherService;
import com.dfire.grade.manager.vo.JsonResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.Resource;

/**
 * User:huangtao
 * Date:2016-03-25
 * description：
 */
@RequestMapping("user")
public class UserController {
    @Resource
    private ITeacherService teacherService;
    @Resource
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
    @RequestMapping("/signup")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public JsonResult signUp(@RequestParam(value = "name", required = true) String name,
                             @RequestParam(value = "mobile", required = true) String mobile,
                             @RequestParam(value = "email", required = false, defaultValue = "") String email,
                             @RequestParam(value = "school", required = true) String school,
                             @RequestParam(value = "pass_word", required = true) String passWord,
                             @RequestParam(value = "type", required = true) Integer type) throws Exception {
        if (type == 1) {
            return studentService.insertStudent(name, school, passWord, mobile, email);
        }
        if (type == 2) {
            return teacherService.insertTeacher(name, mobile, email, school, passWord);
        }
        return JsonResult.failedInstance("");
    }

    @RequestMapping("/sign_in")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public JsonResult signin(@RequestParam(value = "mobile", required = true) String mobile,
                             @RequestParam(value = "pass_word", required = true) String passWord,
                             @RequestParam(value = "type", required = true) Integer type) throws Exception {
        JsonResult result = null;
        if (type == 1) {
            result = studentService.queryRoleByMobile(mobile);
        }
        if (type == 2) {
            result = teacherService.queryRoleByMobile(mobile);
        }
        if(result.){

        }
        return JsonResult.success();
    }
}
