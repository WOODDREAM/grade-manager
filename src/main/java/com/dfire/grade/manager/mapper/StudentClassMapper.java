package com.dfire.grade.manager.mapper;

import com.dfire.grade.manager.bean.Page;
import com.dfire.grade.manager.bean.StudentClass;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User:huangtao
 * Date:2016-04-13
 * description：
 */
@Service
public interface StudentClassMapper {
    /**
     * 创建学生和课程关系（j加入课程）
     *
     * @param studentClass
     * @throws Exception
     */
    void create(StudentClass studentClass) throws Exception;

    /**
     * 根据相应id查找课程关系
     *
     * @param page
     * @return
     * @throws Exception
     */
    List<StudentClass> selectRelationship(Page page) throws Exception;

    /**
     * 根据相应Id删除课程关系
     *
     * @param studentClass
     * @throws Exception
     */
    void deleteById(StudentClass studentClass) throws Exception;

    /**
     * 根据stuId和clasId查课程
     *
     * @param studentClass
     * @return
     * @throws Exception
     */
    StudentClass selectByIdAndMobile(StudentClass studentClass) throws Exception;

    /**
     * 根据关系id查
     *
     * @param relationshipId
     * @return
     * @throws Exception
     */
    StudentClass selectByShipId(String relationshipId) throws Exception;

    void updateAgree(String relationshipId) throws Exception;

    List<StudentClass> selectBatch(@Param("classIds")List<String> classIds) throws Exception;
}
