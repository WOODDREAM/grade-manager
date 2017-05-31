package com.dfire.grade.manager.service.impl;

import com.dfire.grade.manager.Contants;
import com.dfire.grade.manager.bean.*;
import com.dfire.grade.manager.mapper.ClassesMapper;
import com.dfire.grade.manager.mapper.StudentClassMapper;
import com.dfire.grade.manager.service.IClassService;
import com.dfire.grade.manager.service.IStudentClassService;
import com.dfire.grade.manager.service.IStudentService;
import com.dfire.grade.manager.service.ITeacherService;
import com.dfire.grade.manager.utils.DateUtil;
import com.dfire.grade.manager.utils.SequenceUtil;
import com.dfire.grade.manager.vo.JsonResult;
import com.dfire.grade.manager.vo.RelationshipVo;
import com.dfire.grade.manager.vo.Reliation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User:huangtao
 * Date:2016-04-13
 * description：
 */
@Service("studentClassImpl")
public class StudentClassServiceImpl implements IStudentClassService {
    @Autowired
    private StudentClassMapper studentClassMapper;
    @Autowired
    private ITeacherService teacherService;
    @Autowired
    private IStudentService studentService;
    @Autowired
    private IClassService classService;
    @Autowired
    private ClassesMapper classesMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public JsonResult createRelationship(String teacherId, String studentId, String classesId, String mobile, String studentNo, String studentName, String classNo, String teacherName) throws Exception {
        SequenceUtil.isBlank(teacherId, "teacherId不能为空");
        SequenceUtil.isBlank(classesId, "classId不能为空");
        SequenceUtil.isBlank(classNo, "课程码不能为空");
        SequenceUtil.isBlank(mobile, "学生手机号不能为空");
        SequenceUtil.isBlank(studentNo, "学生号不能为空");
        SequenceUtil.isBlank(studentName, "学生姓名不能为空");
        SequenceUtil.isBlank(teacherName, "教室姓名不能为空");
        StudentClass studentClass = new StudentClass();
        studentClass.setClassId(classesId);
        studentClass.setCreateTime(DateUtil.getCurDate(DateUtil.DEFAULT_DATETIME_FORMAT_SEC));
        studentClass.setRelationshipId(SequenceUtil.getSequence());
        studentClass.setTeacherId(teacherId);
        studentClass.setStudentId(studentId);
        studentClass.setAgree(false);
        studentClass.setClassNo(classNo);
        studentClass.setMobile(mobile);
        studentClass.setStudentName(studentName);
        studentClass.setStudentNo(studentNo);
        studentClass.setTeacherName(teacherName);
        studentClass.setValid(true);
        StudentClass st = new StudentClass();
        st.setMobile(mobile);
        st.setClassId(classesId);
        StudentClass studentClasses = studentClassMapper.selectByIdAndMobile(st);
        if (null != studentClasses) {
            return JsonResult.failedInstance(Contants.Message.ERROR_JOIN_CLASS_ALREADY);
        }
        JsonResult teaRe = teacherService.queryRoleById(teacherId);
        JsonResult claRe = classService.selectClassById(classesId);
        if (!StringUtils.isEmpty(studentId)) {
            JsonResult stuRe = studentService.queryRoleById(studentId);
            if (!stuRe.isSuccess() || null == stuRe.getData()) {
                return JsonResult.failedInstance(Contants.Message.ERROR_NO_STUDENT);
            }
        }
        if (!teaRe.isSuccess() || null == teaRe.getData()) {
            return JsonResult.failedInstance(Contants.Message.ERROR_NO_TEACHER);
        }
        if (!claRe.isSuccess() || null == claRe.getData()) {
            return JsonResult.failedInstance(Contants.Message.ERROR_NO_CLASS);
        }
        JsonResult sRe = studentService.queryRoleByMobile(mobile);
        if (sRe.isSuccess() && null != sRe.getData()) {
            SignBean student = (SignBean) sRe.getData();
            studentClass.setStudentId(student.getId());
        }
        studentClassMapper.create(studentClass);
        return JsonResult.jsonSuccessMes(Contants.Message.SUCCESS_REQUEST);
    }

    /**
     * 只提供一学期的内容
     *
     * @param studentId
     * @return
     * @throws Exception
     */
    @Override
    public JsonResult selectRelationship(String claId, String teacherId, String studentId, int index, int pageSize, Date startTime, Date endTime) throws Exception {
        //最低一次取1000条记录
        if (0 == pageSize) {
            pageSize = 1000;
        }
        if (null == startTime) {
            startTime = DateUtil.getFirstHalfYear();
        }
        if (null == endTime) {
            endTime = DateUtil.getSecondHalfYear();
        }
        RelationQuery page = new RelationQuery();
        page.setEndTime(endTime);
        page.setStudentId(studentId);
        page.setPageSize(pageSize);
        page.setStartIndex(index);
        page.setStartTime(startTime);
        page.setTeacherId(teacherId);
        page.setClassId(claId);
        List<StudentClass> studentClasses = studentClassMapper.selectRelationship(page);
        RelationshipVo relationshipVo = null;
        if (!CollectionUtils.isEmpty(studentClasses)) {
            relationshipVo = new RelationshipVo();
            List<Reliation> agreedClasses = new ArrayList<>();
            List<Reliation> notAgreedClasses = new ArrayList<>();
            for (StudentClass studentCla : studentClasses) {
                String classId = studentCla.getClassId();
                Classes classes = classesMapper.selectClassById(classId);
                if (null != classes) {
                    Reliation reliation = new Reliation();
                    reliation.setAgree(studentCla.isAgree());
                    reliation.setClassId(classId);
                    reliation.setClassNo(studentCla.getClassNo());
                    reliation.setCredit(classes.getCredit());
                    reliation.setMobile(studentCla.getMobile());
                    reliation.setClassName(classes.getName());
                    reliation.setClassNo(studentCla.getClassNo());
                    reliation.setPeriod(classes.getPeriod());
                    reliation.setStudentName(studentCla.getStudentName());
                    reliation.setStudentNo(studentCla.getStudentNo());
                    reliation.setTeacherName(studentCla.getTeacherName());
                    reliation.setRelstionId(studentCla.getRelationshipId());
                    if (studentCla.isAgree()) {
                        agreedClasses.add(reliation);
                    } else {
                        notAgreedClasses.add(reliation);
                    }
                }
            }
            if (!CollectionUtils.isEmpty(agreedClasses)) {
                relationshipVo.setAgreeClass(agreedClasses);
            }
            if (!CollectionUtils.isEmpty(notAgreedClasses)) {
                relationshipVo.setNotAgreeClass(notAgreedClasses);
            }
            return JsonResult.jsonSuccessData(relationshipVo);
        }
        return JsonResult.failedInstance("没有正在处理的课程");
    }

    @Override
    public JsonResult deleteById(StudentClass studentClass) throws Exception {
        Assert.notNull(studentClass, "删除条件不能为空！");
        studentClassMapper.deleteById(studentClass);
        return JsonResult.jsonSuccessMes("删除成功！");
    }

    @Override
    public JsonResult updateAgree(String relationshipId) throws Exception {
        SequenceUtil.isBlank(relationshipId, "关系Id不能为空！");
        StudentClass studentClass = studentClassMapper.selectByShipId(relationshipId);
        if (null != studentClass) {
            JsonResult rs = classService.selectClassById(studentClass.getClassId());
            if (rs.isSuccess() && null != rs.getData()) {
                studentClassMapper.updateAgree(relationshipId);
                StudentClass s = studentClassMapper.selectByShipId(studentClass.getRelationshipId());
                return JsonResult.jsonSuccessData(null == s ? null : s);
            } else {
                return JsonResult.failedInstance("未找到相关课程，请删除！");
            }
        }
        return JsonResult.failedInstance("没有此开课课程");
    }

    @Override
    public JsonResult selectIfJoinedById(String mobile, String classId) throws Exception {
        SequenceUtil.isBlank(mobile, "mobile不能为空！");
        SequenceUtil.isBlank(classId, "classId不能为空！");
        StudentClass studentClass = new StudentClass();
        studentClass.setMobile(mobile);
        studentClass.setClassId(classId);
        StudentClass studentClass1 = studentClassMapper.selectByIdAndMobile(studentClass);
        return JsonResult.jsonSuccessData(null == studentClass1 ? null : studentClass1);
    }

    @Override
    public JsonResult selectBatch(List<String> classIds) throws Exception {
        Assert.notEmpty(classIds);
        List<StudentClass> studentClassList = studentClassMapper.selectBatch(classIds);
        if (CollectionUtils.isEmpty(studentClassList)) {
            return JsonResult.jsonSuccessData(null);
        }
        return JsonResult.jsonSuccessData(studentClassList);
    }
}
