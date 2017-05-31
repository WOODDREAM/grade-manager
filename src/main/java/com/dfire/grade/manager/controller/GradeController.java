package com.dfire.grade.manager.controller;

import com.dfire.grade.manager.Contants;
import com.dfire.grade.manager.bean.SignBean;
import com.dfire.grade.manager.service.IGradeService;
import com.dfire.grade.manager.service.ITeacherService;
import com.dfire.grade.manager.utils.RedisUtil;
import com.dfire.grade.manager.vo.JsonResult;
import com.dfire.grade.manager.vo.form.GradeForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * User:huangtao
 * Date:2016-04-17
 * description：
 */
@Controller
@RequestMapping("/grade")
public class GradeController extends BaseController {
    @Autowired
    private IGradeService gradeService;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    private ITeacherService teacherService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public JsonResult addGrade(HttpServletRequest request,
                               @RequestParam(value = "grade", required = true) Double grade,
                               @RequestParam(value = "answerId", required = true) String answerId) throws Exception {
        SignBean signBean = (SignBean) request.getSession().getAttribute(Contants.TEACHER_KEY);
        if (null != signBean) {
            String teacherId = signBean.getId();
            JsonResult teaRe = teacherService.queryRoleById(teacherId);
            if (teaRe.isSuccess() && null != teaRe.getData()) {
                return gradeService.addGrade(teacherId,grade,answerId);
            }
        }
        return JsonResult.failedInstance(Contants.Message.NOT_PERMISSION);
    }

    @RequestMapping(value = "/create/batch", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public JsonResult addGradeBatch(HttpServletRequest request, @RequestParam(value = "grades", required = true) List<GradeForm> gradeForms) throws Exception {
        SignBean signBean = (SignBean) request.getSession().getAttribute(Contants.TEACHER_KEY);
        if (null != signBean) {
            String teacherId = signBean.getId();
            JsonResult teaRe = teacherService.queryRoleById(teacherId);
            if (teaRe.isSuccess() && null != teaRe.getData()) {
                return gradeService.insertBatch(teacherId, gradeForms);
            }
        }
        return JsonResult.failedInstance(Contants.Message.NOT_PERMISSION);
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public JsonResult findGrade(@RequestParam(value = "grade_id", required = true) String gradeId) throws Exception {
        return gradeService.selectGradeById(gradeId);
    }


}
