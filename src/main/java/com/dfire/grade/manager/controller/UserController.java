package com.dfire.grade.manager.controller;

import com.dfire.grade.manager.service.IRoleService;
import com.dfire.grade.manager.service.ITeacherService;
import com.dfire.grade.manager.vo.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    private IRoleService studentService;

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
    public JsonResult signUp(@RequestParam(value = "name", required = true) String name,
                             @RequestParam(value = "mobile", required = true) String mobile,
                             @RequestParam(value = "email", required = false, defaultValue = "") String email,
                             @RequestParam(value = "school", required = true) String school,
                             @RequestParam(value = "pass_word", required = true) String passWord,
                             @RequestParam(value = "type", required = true) Integer type) throws Exception {
        if (type == 1) {
            studentService.insertStudent(name, school, passWord, mobile, email);
        }
        if (type == 2) {
            teacherService.insertTeacher(name, mobile, email, school, passWord);
        }
        return JsonResult.success();
    }

    public JsonResult signin(@RequestParam(value = "mobile", required = true) String mobile,
                             @RequestParam(value = "pass_word", required = true) String passWord,
                             @RequestParam(value = "type", required = true) Integer type) {
        if (type == 1) {
        }
        if (type == 2) {
        }
        return JsonResult.success();
    }
}
