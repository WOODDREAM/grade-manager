package com.dfire.grade.manager.controller;

import com.dfire.grade.manager.Contants;
import com.dfire.grade.manager.bean.Answer;
import com.dfire.grade.manager.bean.SignBean;
import com.dfire.grade.manager.service.*;
import com.dfire.grade.manager.utils.DateUtil;
import com.dfire.grade.manager.vo.AnswerVo;
import com.dfire.grade.manager.vo.JobVo;
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
import java.util.*;

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
    @Autowired
    private IStudentClassService studentClassService;
    @Autowired
    private IAnswerService answerService;

    @RequestMapping(value = "/create", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseStatus(HttpStatus.OK)
    public String createJob(HttpServletRequest request, Model model,
                            @RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "className", required = false) String className,
                            @RequestParam(value = "classId", required = false) String classId,
                            @RequestParam(value = "isNeedAnswer", required = false) Boolean isAnswer,
                            @RequestParam(value = "type", required = false) Integer type,
                            @RequestParam(value = "endTime", required = false) String endTime,
                            @RequestParam(value = "detail", required = false) String detail) throws Exception {

        if (request.getMethod().equals(Contants.Http.METHOD_GET)) {
            if (!StringUtils.isEmpty(classId)) {
                model.addAttribute("classId", classId);
                model.addAttribute("className", className);
                return "job/usual_create";
            }
            return "job/create";
        }
        if (request.getMethod().equals(Contants.Http.METHOD_POST)) {
            SignBean signBean = (SignBean) request.getSession().getAttribute(Contants.TEACHER_KEY);
            String teacherId = signBean.getId();
            JsonResult teaRe = teacherService.queryRoleById(teacherId);
            if (teaRe.isSuccess() && null != teaRe.getData()) {
                if (null != type) {
                    if (type == 1 && StringUtils.isEmpty(classId)) {
                        model.addAttribute("message", "请选择课程！");
                    } else {
                        Date date = DateUtil.parseDate(endTime, DateUtil.DEFAULT_MOUTH_DAY_YEAR);
                        JsonResult jobResult = jobService.createJob(teacherId, classId, name, detail, type, isAnswer, date);
                        if (jobResult.isSuccess()) {
                            model.addAttribute("jobDetail", jobResult.getData());
                            return "job/detail";
                        } else {
                            model.addAttribute("message", jobResult.getMessage());
                        }
                    }
                } else {
                    model.addAttribute("message", "没有作业类型！");
                }
            } else {
                model.addAttribute("message", Contants.Message.NOT_PERMISSION);
            }
        }
        if (type == Contants.Type.TERM_JOB) {
            return "job/create";
        } else {
            model.addAttribute("classId", classId);
            model.addAttribute("className", name);
            return "job/usual_create";
        }
    }

    @RequestMapping(value = "/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseStatus(HttpStatus.OK)
    public String updateJob(HttpServletRequest request, Model model,
                            @RequestParam(value = "jobId", required = true) String jobId,
                            @RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "className", required = false) String className,
                            @RequestParam(value = "classId", required = false) String classId,
                            @RequestParam(value = "isNeedAnswer", required = false) Boolean isAnswer,
                            @RequestParam(value = "endTime", required = false) String endTime,
                            @RequestParam(value = "detail", required = false) String detail) throws Exception {
        if (request.getMethod().equals(Contants.Http.METHOD_GET)) {
            if (!StringUtils.isEmpty(jobId)) {
                JsonResult result = jobService.selectJobById(jobId);
                if (result.isSuccess() && null != result.getData()) {
                    model.addAttribute("jobDetail", result.getData());
                } else {
                    model.addAttribute("message", "没有查到此作业！");
                }
            } else {
                model.addAttribute("message", "参数为空！");
            }
        }
        if (request.getMethod().equals(Contants.Http.METHOD_POST)) {
            JobVo job = new JobVo();
            job.setJobId(jobId);
            job.setDetail(detail);
            job.setAnswer(isAnswer);
            job.setName(name);
            job.setEndTime(endTime);
            job.setClassId(classId);
            job.setClassName(className);
            model.addAttribute("jobDetail", job);
            SignBean signBean = (SignBean) request.getSession().getAttribute(Contants.TEACHER_KEY);
            String teacherId = signBean.getId();
            JsonResult teaRe = teacherService.queryRoleById(teacherId);
            if (teaRe.isSuccess() && null != teaRe.getData()) {
                Date date = new Date();
                if (endTime.contains("/")) {
                    date = DateUtil.parseDate(endTime, DateUtil.DEFAULT_MOUTH_DAY_YEAR);
                } else {
                    date = DateUtil.parseDate(endTime, DateUtil.DEFAULT_DATE_FORMAT);
                }
                JsonResult result = jobService.updateJob(teacherId, name, detail, isAnswer, jobId, date);
                if (result.isSuccess() && null != result.getData()) {
                    model.addAttribute("jobDetail", result.getData());
                    return "job/detail";
                } else {
                    model.addAttribute("message", "创建失败！");
                }
            } else {
                model.addAttribute("message", Contants.Message.NOT_PERMISSION);
            }
        }
        return "job/update";
    }

    @RequestMapping(value = "/find", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseStatus(HttpStatus.OK)
    public String findJob(HttpServletRequest request, Model model,
                          @RequestParam(value = "pageSize", required = false, defaultValue = "1000") Integer pageSize,
                          @RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
                          @RequestParam(value = "studentId", required = false) String studentId,
                          @RequestParam(value = "teacherId", required = false) String teacherId,
                          @RequestParam(value = "classId", required = false) String classId,
                          @RequestParam(value = "message", required = false) String message,
                          @RequestParam(value = "jobId", required = false) String jobId) throws Exception {
        SignBean signBean = (SignBean) request.getSession().getAttribute(Contants.USER_KEY);
        String id = signBean.getId();
        JsonResult result = teacherService.queryRoleById(id);
        if (!result.isSuccess() || null == result.getData()) {
            result = studentService.queryRoleById(id);
            if (StringUtils.isEmpty(studentId)) {
                studentId = id;
                model.addAttribute("roleType", 1);
            }
        } else {
            if (StringUtils.isEmpty(teacherId)) {
                teacherId = id;
                model.addAttribute("roleType", 2);
            }
        }
        if (result.isSuccess() && null != result.getData()) {
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
            if (null == pageSize) {
                pageSize = 1000;
            }
            map.put("pageSize", pageSize);
            if (null == pageIndex) {
                pageIndex = 1;
            }
            int index = (pageIndex - 1) * 10 - 1;
            map.put("index", index == -1 ? 0 : index);
            JsonResult rs = jobService.selectJob(map);
            if (rs.isSuccess()) {
                if (null != rs.getData() && !CollectionUtils.isEmpty((Collection<?>) rs.getData())) {
                    List<JobVo> jobVos = (List<JobVo>) rs.getData();
                    if (!StringUtils.isEmpty(studentId)) {
                        Answer answer = new Answer();
                        answer.setStudentId(studentId);
                        JsonResult ansRe = answerService.selectAnswerByCondition(answer);
                        if (ansRe.isSuccess() && null != ansRe.getData()) {
                            List<AnswerVo> answerVoList = (List<AnswerVo>) ansRe.getData();
                            for (int i = 0; i < jobVos.size(); i++) {
                                for (AnswerVo answerVo : answerVoList) {
                                    if (jobVos.get(i).getJobId().equals(answerVo.getJobId())) {
                                        if (answerVo.isAnswered()) {
                                            jobVos.get(i).setAnswered(true);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    model.addAttribute("jobList", jobVos);
                    model.addAttribute("message", message);
                } else {
                    model.addAttribute("message", rs.getMessage());
                }
            } else {
                model.addAttribute("message", Contants.Message.ERROR_REQUEST);
            }
        }
        return "job/list";
    }

    @RequestMapping(value = "/detail", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseStatus(HttpStatus.OK)
    public String findJobById(HttpServletRequest request, Model model,
                              @RequestParam(value = "jobId", required = true) String jobId) throws Exception {
        if (!StringUtils.isEmpty(jobId)) {
            JsonResult rs = jobService.selectJobById(jobId);
            if (rs.isSuccess() && null != rs.getData()) {
                model.addAttribute("jobDetail", rs.getData());
            } else {
                model.addAttribute("message", rs.getMessage());
            }
        } else {
            model.addAttribute("message", "jobId为空！");
        }
        return "job/detail";
    }

    @RequestMapping(value = "/student", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseStatus(HttpStatus.OK)
    public String findJobOfStudent(HttpServletRequest request, Model model,
                                   @RequestParam(value = "startTime", required = false) Date startTime,
                                   @RequestParam(value = "end_time", required = false) Date endTime,
                                   @RequestParam(value = "pageSize", required = false, defaultValue = "1000") Integer pageSize,
                                   @RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex) throws Exception {
        SignBean signBean = (SignBean) request.getSession().getAttribute(Contants.STUDENT_KEY);
        String studentId = signBean.getId();
        JsonResult stuRe = studentService.queryRoleById(studentId);
        if (stuRe.isSuccess() && null != stuRe.getData()) {
            int index = ((pageIndex - 1) * 10) - 1;
            if (-1 == index) {
                index = 0;
            }
            JsonResult result = studentClassService.selectRelationship(null, null, studentId, index, pageSize, startTime, endTime);
            if (result.isSuccess() && null != result.getData()) {

            } else {
                model.addAttribute("message", "您还没有申请加入课程！");
            }
        } else {
            model.addAttribute("message", "jobId为空！");
        }
        return "job/list";
    }
}
