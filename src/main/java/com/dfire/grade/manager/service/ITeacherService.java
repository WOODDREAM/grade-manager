package com.dfire.grade.manager.service;

import com.dfire.grade.manager.vo.JsonResult;

/**
 * User:huangtao
 * Date:2016-03-30
 * description：
 */
public interface ITeacherService {

    JsonResult insertTeacher(String name, String school, String passWord, String mobile, String email, int sex) throws Exception;

    JsonResult queryRoleById(String id) throws Exception;

    JsonResult queryRoleByMobile(String mobile) throws Exception;

    JsonResult modifyPassword(String id, String passWord) throws Exception;

    JsonResult modifyMobile(String id, String mobile) throws Exception;

    JsonResult modifyInfo(String id, String email,String school,String name) throws Exception;

    JsonResult selectTeacherUnderStudent(String studentId) throws Exception;

}
