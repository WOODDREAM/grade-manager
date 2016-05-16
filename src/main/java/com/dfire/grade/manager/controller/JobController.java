package com.dfire.grade.manager.controller;

import com.dfire.grade.manager.Contants;
import com.dfire.grade.manager.bean.SignBean;
import com.dfire.grade.manager.service.IJobService;
import com.dfire.grade.manager.service.IStudentService;
import com.dfire.grade.manager.service.ITeacherService;
import com.dfire.grade.manager.utils.DateUtil;
import com.dfire.grade.manager.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * User:huangtao
 * Date:2016-04-13
 * description：
 */
@Controller
@RequestMapping("/job")
public class JobController extends BaseController {
    @Autowired
    private IJobService jobService;
    @Autowired
    private ITeacherService teacherService;
    @Autowired
    private IStudentService studentService;

    @RequestMapping(value = "/create", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseStatus(HttpStatus.OK)
    public String createJob(HttpServletRequest request, Model model,
                            @RequestParam(value = "jobId", required = false, defaultValue = "") String jobId,
                            @RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "classId", required = false) String classId,
                            @RequestParam(value = "isNeedAnswer", required = false) Boolean isAnswer,
                            @RequestParam(value = "type", required = false) Integer type,
                            @RequestParam(value = "endTime", required = false) String endTime,
                            @RequestParam(value = "detail", required = true, defaultValue = "") String detail) throws Exception {
        if (request.getMethod().equals(Contants.Http.METHOD_POST)) {
            SignBean signBean = (SignBean) request.getSession().getAttribute(Contants.TEACHER_KEY);
            String teacherId = signBean.getId();
            JsonResult teaRe = teacherService.queryRoleById(teacherId);
            if (teaRe.isSuccess() && null != teaRe.getData()) {
                Date date = DateUtil.parseDate(endTime, DateUtil.DEFAULT_MOUTH_DAY_YEAR);
                JsonResult jobResult = jobService.createJob(teacherId, classId, name, detail, type, isAnswer, jobId, date);
                if (jobResult.isSuccess()) {
                    model.addAttribute("jobDetail", jobResult.getData());
                    return "job/detail";
                } else {
                    model.addAttribute("message", jobResult.getMessage());
                }
            } else {
                model.addAttribute("message", Contants.Message.NOT_PERMISSION);
            }
        }
        return "job/create";
    }

    @RequestMapping(value = "/find", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseStatus(HttpStatus.OK)
    public String findAnswer(HttpServletRequest request, Model model,
                             @RequestParam(value = "studentId", required = false) String studentId,
                             @RequestParam(value = "teacherId", required = false) String teacherId,
                             @RequestParam(value = "classId", required = false) String classId,
                             @RequestParam(value = "jobId", required = false) String jobId) throws Exception {
        SignBean signBean = (SignBean) request.getSession().getAttribute(Contants.USER_KEY);
        String id = signBean.getId();
        JsonResult result = teacherService.queryRoleById(id);
        if (!result.isSuccess() || null == result.getData()) {
            result = studentService.queryRoleById(id);
            if (StringUtils.isEmpty(studentId)) {
                studentId = id;
            }
        } else {
            if (StringUtils.isEmpty(teacherId)) {
                teacherId = id;
            }
        }
        if (result.isSuccess() && null == result.getData()) {
            Map<String, Object> map = new HashMap<>();
            if (!StringUtils.isEmpty(studentId)) {
                map.put("studentId", studentId);
            }
            if (!StringUtils.isEmpty(teacherId)) {
                map.put("teacherId", teacherId);
            }
            if (!StringUtils.isEmpty(jobId)) {
                map.put("jobId", jobId);
            }
            if (!StringUtils.isEmpty(classId)) {
                map.put("classId", classId);
            }
            JsonResult rs = jobService.selectJob(map);
            if (rs.isSuccess()) {
                if (null != rs.getData() && !CollectionUtils.isEmpty((Collection<?>) rs.getData())) {
                    model.addAttribute("jobList", rs.getData());
                }
                model.addAttribute("message", rs.getMessage());
            } else {
                model.addAttribute("message", Contants.Message.ERROR_REQUEST);
            }
        }

        return "job/list";
    }
}
