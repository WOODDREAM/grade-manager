package com.dfire.grade.manager.service.impl;

import com.dfire.grade.manager.Contants;
import com.dfire.grade.manager.bean.Answer;
import com.dfire.grade.manager.bean.Grade;
import com.dfire.grade.manager.mapper.GradeMapper;
import com.dfire.grade.manager.service.*;
import com.dfire.grade.manager.utils.DateUtil;
import com.dfire.grade.manager.utils.RedisUtil;
import com.dfire.grade.manager.utils.SequenceUtil;
import com.dfire.grade.manager.vo.AnswerVo;
import com.dfire.grade.manager.vo.JsonResult;
import com.dfire.grade.manager.vo.form.GradeForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * User:huangtao
 * Date:2016-04-17
 * description：
 */
@Service
public class GradeServiceImpl implements IGradeService {
    @Autowired
    private GradeMapper gradeMapper;
    @Autowired
    private IStudentService studentService;
    @Autowired
    private IJobService jobService;
    @Autowired
    private IClassService classService;
    @Autowired
    private IAnswerService answerService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * @return
     * @throws Exception
     */
    @Override
    public JsonResult addGrade(String teacherId, double grade, String answerId) throws Exception {
        SequenceUtil.isBlank(answerId, "answerId不能为空！");
        Answer answer = new Answer();
        answer.setAnswer(answerId);
        JsonResult ansRe = answerService.selectAnswerByCondition(answer);
        if (ansRe.isSuccess() && null != ansRe.getData()) {
            List<AnswerVo> answerVoList = (List<AnswerVo>) ansRe.getData();
            AnswerVo answerVo = answerVoList.get(0);
            JsonResult createRe = createGrade(answerId, answerVo.getStudentId(), answerVo.getClassId(), teacherId, grade, answerVo.getJobId());
            if (createRe.isSuccess() && null != createRe.getData()) {
                Grade newGrade = (Grade) createRe.getData();
                gradeMapper.insertGrade(newGrade);
                return JsonResult.jsonSuccessData(newGrade.getGrade());
            } else {
                return createRe;
            }
        } else {
            return ansRe;
        }
    }


    @Override
    public JsonResult selectGradeById(String gradeId) throws Exception {
        SequenceUtil.isBlank(gradeId, "gradeId不能为空！");

        Grade grade = gradeMapper.selectGradeById(gradeId);
        return JsonResult.jsonSuccessData(grade);
    }


    @Override
    public JsonResult insertBatch(String teacherId, List<GradeForm> gradeForms) throws Exception {
        List<Grade> grades = new ArrayList<>();
//        for (GradeForm gradeForm : gradeForms) {
//            String studentId = gradeForm.getStudentId();
//            String classId = gradeForm.getClassId();
//            double grade = gradeForm.getGrade();
//            String jobId = gradeForm.getJobId();
//            int type = Integer.getInteger(gradeForm.getType());
//            JsonResult re = createGrade(studentId, classId, teacherId, grade, jobId);
//            if (re.isSuccess()) {
//
//            }
//        }
        return null;
    }

    @Override
    public JsonResult selectByAnswerId(String answerId) throws Exception {
        return null;
    }

    private JsonResult createGrade(String answerId, String studentId, String classId, String teacherId, double grade, String jobId) throws Exception {
        SequenceUtil.isBlank(studentId, "studentId不能为空！");
        SequenceUtil.isBlank(answerId, "answerId不能为空！");
        SequenceUtil.isBlank(classId, "classId不能为空！");
        SequenceUtil.isBlank(teacherId, "teacherId不能为空！");
        SequenceUtil.isBlank(jobId, "jobId不能为空！");
        SequenceUtil.isBlank(String.valueOf(grade), "grade不能为空！");
        JsonResult result = studentService.queryRoleById(studentId);
        if (result.isSuccess()) {
            if (null != result.getData()) {
                JsonResult classResult = classService.selectClassById(classId);
                if (classResult.isSuccess()) {
                    if (null != classResult.getData()) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("jobId", jobId);
                        JsonResult jobResult = jobService.selectJob(map);
                        if (jobResult.isSuccess()) {
                            if (null != jobResult.getData() && !CollectionUtils.isEmpty((Collection<?>) jobResult.getData())) {
                                Grade newGrade = new Grade();
                                newGrade.setClassId(classId);
                                newGrade.setCreateTime(DateUtil.getCurDate());
                                newGrade.setGrade(grade);
                                newGrade.setGradeId(SequenceUtil.getSequence());
                                newGrade.setJobId(jobId);
                                newGrade.setStudentId(studentId);
                                newGrade.setTeacherId(teacherId);
                                newGrade.setValid(true);
                                return JsonResult.jsonSuccessData(newGrade);
                            } else {
                                return JsonResult.failedInstance(Contants.Message.NOT_FIND_JOB);
                            }
                        }
                    } else {
                        return JsonResult.failedInstance(Contants.Message.ERROR_NO_CLASS);
                    }
                }
            } else {
                return JsonResult.failedInstance(Contants.Message.ERROR_NO_STUDENT);
            }
        }
        return JsonResult.failedInstance(Contants.Message.ERROR_REQUEST);
    }
}
