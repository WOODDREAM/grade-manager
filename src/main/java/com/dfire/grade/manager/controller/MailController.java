package com.dfire.grade.manager.controller;

import com.dfire.grade.manager.Contants;
import com.dfire.grade.manager.bean.Email;
import com.dfire.grade.manager.bean.SignBean;
import com.dfire.grade.manager.bean.Student;
import com.dfire.grade.manager.bean.Teacher;
import com.dfire.grade.manager.service.IEmailService;
import com.dfire.grade.manager.service.IStudentService;
import com.dfire.grade.manager.service.ITeacherService;
import com.dfire.grade.manager.utils.MailUtil;
import com.dfire.grade.manager.vo.EmailVo;
import com.dfire.grade.manager.vo.EmailVoForRead;
import com.dfire.grade.manager.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.util.comparator.BooleanComparator;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * User:huangtao
 * Date:2016-04-17
 * description：
 */
@Controller
@RequestMapping("/email")
public class MailController extends BaseController {
    @Autowired
    private MailUtil mailUtil;
    @Autowired
    private IStudentService studentService;
    @Autowired
    private ITeacherService teacherService;
    @Autowired
    private IEmailService emailService;


    @RequestMapping(value = "/mail", method = RequestMethod.GET)
    public String sendMail() throws MessagingException, UnsupportedEncodingException {
        mailUtil.sendMail("殷茹梦的信息", "没事发个邮件给你O(∩_∩)O哈哈~", "F:\\学习资料\\半年计划\\Git\\Pro Git.pdf", null, "huangtao@2dfire.com");
        return "hello";
    }


    @RequestMapping(value = "/message", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String sendSMS(HttpServletRequest request, HttpServlet servlet, Model model) throws Exception {
        String emailToManId = (String) request.getSession().getAttribute("emailToManId");
        String fileName = (String) request.getSession().getAttribute("fileName");
        String subject = (String) request.getSession().getAttribute("subject");
        String body = (String) request.getSession().getAttribute("body");
        request.getSession().removeAttribute("emailToManId");
        request.getSession().removeAttribute("fileName");
        request.getSession().removeAttribute("subject");
        request.getSession().removeAttribute("body");
        int roleType = 1;
        String teacherId = null;
        String studentId = null;
        if (!StringUtils.isEmpty(emailToManId) || !StringUtils.isEmpty(emailToManId)) {
            SignBean signBean = (SignBean) request.getSession().getAttribute(Contants.STUDENT_KEY);

            JsonResult roleRe;
            String address;
            if (null == signBean) {
                signBean = (SignBean) request.getSession().getAttribute(Contants.TEACHER_KEY);
                roleRe = studentService.queryRoleById(signBean.getId());
                teacherId = signBean.getId();
                Student student = (Student) roleRe.getData();
                address = student.getEmail();
                roleType = 2;
            } else {
                roleRe = teacherService.queryRoleById(signBean.getId());
                Teacher teacher = (Teacher) roleRe.getData();
                studentId = signBean.getId();
                address = teacher.getEmail();
            }
            mailUtil.sendMail(subject, body, fileName, servlet, address);
            JsonResult result = emailService.insertEmail(body, subject, teacherId, studentId, address, fileName);
            if (result.isSuccess() && null != result.getData()) {
                model.addAttribute("message", "邮件已发送！");
            } else {
                model.addAttribute("message", result.getMessage());
            }
        } else {
            model.addAttribute("message", "请选择收件人！");
        }
        Email email = new Email();
        email.setStudentId(studentId);
        email.setTeacherId(teacherId);
        JsonResult emailResult = emailService.selectUnreadCount(email);
        if (emailResult.isSuccess() && null != emailResult.getData()) {
            model.addAttribute("unreadEmailCount", emailResult.getData());
        }
        model.addAttribute("type", roleType);
        return "index";
    }

    @RequestMapping("/read")
    @ResponseStatus(HttpStatus.OK)
    public String readeEmail(HttpServletRequest request, Model model, @RequestParam(value = "id", required = true) String id) throws Exception {
        if (!StringUtils.isEmpty(id)) {
            emailService.makeReaded(id);
            Email email = new Email();
            email.setId(id);
            JsonResult result = emailService.findEmail(email);
            if (result.isSuccess() && null != result.getData()) {
                List<Email> emailList = (List<Email>) result.getData();
                model.addAttribute("email", emailList.get(0));
            } else {
                model.addAttribute("message", Contants.Message.ERROR_REQUEST);
            }
        } else {
            model.addAttribute("message", "参数为空！");
        }
        return "email/detail";
    }

    @RequestMapping("/find")
    @ResponseStatus(HttpStatus.OK)
    public String findEmail(HttpServletRequest request, Model model,
                            @RequestParam(value = "received", required = false) boolean received,
                            @RequestParam(value = "important", required = false) boolean important,
                            @RequestParam(value = "delete", required = false) boolean delete,
                            @RequestParam(value = "send", required = false) boolean send) throws Exception {
        SignBean signBean = (SignBean) request.getSession().getAttribute(Contants.TEACHER_KEY);
        String studentId = null;
        String teacherId = null;
        if (null == signBean) {
            signBean = (SignBean) request.getSession().getAttribute(Contants.STUDENT_KEY);
            studentId = signBean.getId();
            model.addAttribute("roleType", 1);
        } else {
            teacherId = signBean.getId();
            model.addAttribute("roleType", 2);
        }
        Email email = new Email();
        email.setTeacherId(teacherId);
        email.setStudentId(studentId);
        email.setValid(true);
        JsonResult result = emailService.findEmail(email);
        if (result.isSuccess() && null != result.getData()) {
            EmailVoForRead emailVoForRead = (EmailVoForRead) result.getData();
            List<EmailVo> readedEmails = emailVoForRead.getReadedEmails();
            List<EmailVo> unReadEmails = emailVoForRead.getUnReadEmails();
            List<EmailVo> sendEmails = emailVoForRead.getSendEmails();
            List<EmailVo> deletedEmails = emailVoForRead.getDeletedEmails();
            List<EmailVo> importantEmails = emailVoForRead.getImportantEmails();
            if (!CollectionUtils.isEmpty(readedEmails)) {
                model.addAttribute("readedEmails", readedEmails);
            }
            if (!CollectionUtils.isEmpty(unReadEmails)) {
                model.addAttribute("unReadEmails", unReadEmails);
                model.addAttribute("unReadEmailsCount", unReadEmails.size());
            }
            if (!CollectionUtils.isEmpty(sendEmails)) {
                model.addAttribute("sendEmails", sendEmails);
            }
            if (!CollectionUtils.isEmpty(deletedEmails)) {
                model.addAttribute("deletedEmails", deletedEmails);
            }
            if (!CollectionUtils.isEmpty(importantEmails)) {
                model.addAttribute("importantEmails", importantEmails);
            }

        } else {
            model.addAttribute("message", "没有邮件");
        }
        model.addAttribute("received", received);
        model.addAttribute("important", important);
        model.addAttribute("delete", delete);
        model.addAttribute("send", send);
        model.addAttribute("signBean", signBean);
        return "email/list";
    }

}
