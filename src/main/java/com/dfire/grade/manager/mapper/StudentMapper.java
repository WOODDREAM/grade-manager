package com.dfire.grade.manager.mapper;

import com.dfire.grade.manager.bean.Student;
import org.springframework.stereotype.Service;

/**
 * User:huangtao
 * Date:2016-02-27
 * description：
 */
@Service
public interface StudentMapper {
    void insertStudent(Student student);

    Student queryStudentById(String id);
}
