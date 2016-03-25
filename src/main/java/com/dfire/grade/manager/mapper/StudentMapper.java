package com.dfire.grade.manager.mapper;

import com.dfire.grade.manager.bean.SignBean;
import com.dfire.grade.manager.bean.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * User:huangtao
 * Date:2016-02-27
 * description：
 */
@Service
public interface StudentMapper {
    void insertStudent(Student student);

    /**
     * 通过id查询学生
     *
     * @param id
     * @return
     */
    Student queryStudentById(String id);

    /**
     * 通过手机号查询学生
     *
     * @param mobile
     * @return
     */
    SignBean selectStudentByMobile(String mobile);

    /**
     * 修改密码
     *
     * @param map passWord studentId
     */
    void modifyPassword(Map<String, String> map);

    /**
     * 修改手机号
     *
     * @param map mobile studentId
     */
    void modifyMobile(Map<String, String> map);

    /**
     * 修改email
     *
     * @param map email studentId
     */
    void modifyEmail(Map<String, String> map);

    /**
     * 查询课程内所有的学时
     *
     * @param classId
     * @return
     */
    List<Student> selectStudentsInClass(String classId);

    /**
     * 查询教师名下的所有学生
     *
     * @param teacherId
     * @return
     */
    List<Student> selectStudentUnderTeacher(String teacherId);
}
