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
    void insertStudent(Student student) throws Exception;

    /**
     * 通过id查询学生
     *
     * @param id
     * @return
     */
    Student queryStudentById(String id) throws Exception;

    /**
     * 通过手机号查询学生
     *
     * @param mobile
     * @return
     */
    SignBean selectStudentByMobile(String mobile) throws Exception;

    /**
     * 修改密码
     *
     * @param student passWord studentId(id,passWord)
     */
    void modifyPassword(Student student) throws Exception;

    /**
     * 修改手机号
     *
     * @param student mobile studentId(id,mobile)
     */
    void modifyMobile(Student student) throws Exception;

    /**
     * 修改email
     *
     * @param student email studentId(id,email)
     */
    void modifyInfo(Student student) throws Exception;

    /**
     * 查询课程内所有的学时
     *
     * @param classId
     * @return
     */
    List<Student> selectStudentsInClass(String classId) throws Exception;

    /**
     * 查询教师名下的所有学生
     *
     * @param teacherId
     * @return
     */
    List<Student> selectStudentUnderTeacher(String teacherId) throws Exception;
}
