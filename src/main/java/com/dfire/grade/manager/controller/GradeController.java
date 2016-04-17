package com.dfire.grade.manager.controller;

import com.dfire.grade.manager.Contants;
import com.dfire.grade.manager.bean.SignBean;
import com.dfire.grade.manager.service.IGradeService;
import com.dfire.grade.manager.utils.RedisUtil;
import com.dfire.grade.manager.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * User:huangtao
 * Date:2016-04-17
 * description：
 */
@RestController
@RequestMapping("/grade")
public class GradeController extends BaseController {
    @Autowired
    private IGradeService gradeService;
    @Autowired
    RedisUtil redisUtil;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public JsonResult addGrade(HttpServletRequest request,
                               @RequestParam(value = "grade", required = true) Double grade,
                               @RequestParam(value = "student_id", required = true) String studentId,
                               @RequestParam(value = "class_id", required = true) String classId,
                               @RequestParam(value = "job_id", required = true) String jobId,
                               @RequestParam(value = "type", required = true) Integer tyep) throws Exception {
        SignBean signBean = (SignBean) redisUtil.getValue(Contants.RedisContent.TEACHER_SIGN_CACHE_BY_ID + request.getHeader(Contants.UID), SignBean.class);
        String teacherId = signBean.getId();
        JsonResult result = gradeService.addGrade(studentId, classId, teacherId, grade, jobId, tyep);
        return result;
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public JsonResult findGrade(@RequestParam(value = "grade_id", required = true) String gradeId) throws Exception {
        return gradeService.selectGradeById(gradeId);
    }
}
