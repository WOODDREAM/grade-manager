package com.dfire.grade.manager.controller;

import com.dfire.grade.manager.Contants;
import com.dfire.grade.manager.bean.Answer;
import com.dfire.grade.manager.bean.SignBean;
import com.dfire.grade.manager.service.IAnswerService;
import com.dfire.grade.manager.service.IStudentService;
import com.dfire.grade.manager.service.ITeacherService;
import com.dfire.grade.manager.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * User:huangtao
 * Date:2016-04-17
 * description：
 */
@Controller
@RequestMapping("/answer")
public class AnswerController {
    @Autowired
    private IAnswerService answerService;
    @Autowired
    private ITeacherService teacherService;
    @Autowired
    private IStudentService studentService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public JsonResult createAnswer(HttpServletRequest request,
                                   @RequestParam(value = "s", required = true) String jobId,
                                   @RequestParam(required = true, value = "f") String answer) throws Exception {
        SignBean signBean = (SignBean) request.getSession().getAttribute(Contants.STUDENT_KEY);
        String studentId = signBean.getId();
        return answerService.createAnswer(studentId, jobId, answer);
    }

    @RequestMapping(value = "/find")
    @ResponseStatus(HttpStatus.OK)
    public String findAnswer(HttpServletRequest request, Model model,
                             @RequestParam(value = "answerId", required = false, defaultValue = "") String answerId,
                             @RequestParam(value = "jobId", required = false, defaultValue = "") String jobId) throws Exception {
        SignBean signBean = (SignBean) request.getSession().getAttribute(Contants.USER_KEY);
        String studentId = signBean.getId();
        JsonResult result = studentService.queryRoleById(studentId);
        Answer answer = new Answer();
        if (result.isSuccess() && null != result.getData()) {
            model.addAttribute("roleType", 1);
            answer.setStudentId(studentId);
        } else {
            result = teacherService.queryRoleById(studentId);
            if (result.isSuccess() && null != result.getData()) {
                model.addAttribute("roleType", 2);
                answer.setTeacherId(studentId);
            } else {
                model.addAttribute("message", "没有权限，请登录");
                return "answer/list";
            }
        }

        if (!answerId.isEmpty()) {
            answer.setAnswerId(answerId);
        }
        if (!jobId.isEmpty()) {
            answer.setJobId(jobId);
        }
        JsonResult answerResult = answerService.selectAnswerByCondition(answer);
        if (answerResult.isSuccess()) {
            if (null != answerResult.getData()) {
                model.addAttribute("answers", answerResult.getData());
            }
        }
        return "answer/list";
    }
}
