package com.dfire.grade.manager.service;

import com.dfire.grade.manager.vo.JsonResult;

/**
 * User:huangtao
 * Date:2016-03-25
 * description：
 */
public interface IStudentService {
    JsonResult insertStudent(String name, String school, String passWord, String mobile, String email) throws Exception;

    JsonResult queryStudentById(String id) throws Exception;

    JsonResult queryStudentByMobile(String mobile) throws Exception;
}
