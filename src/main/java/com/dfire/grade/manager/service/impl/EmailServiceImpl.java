package com.dfire.grade.manager.service.impl;

import com.dfire.grade.manager.Contants;
import com.dfire.grade.manager.bean.Email;
import com.dfire.grade.manager.bean.Student;
import com.dfire.grade.manager.bean.StudentClass;
import com.dfire.grade.manager.bean.Teacher;
import com.dfire.grade.manager.mapper.EmailMapper;
import com.dfire.grade.manager.service.IInboxService;
import com.dfire.grade.manager.service.IStudentClassService;
import com.dfire.grade.manager.service.IStudentService;
import com.dfire.grade.manager.service.ITeacherService;
import com.dfire.grade.manager.utils.DateUtil;
import com.dfire.grade.manager.utils.SequenceUtil;
import com.dfire.grade.manager.vo.EmailVo;
import com.dfire.grade.manager.vo.EmailVoForRead;
import com.dfire.grade.manager.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yin on 2016/5/22.
 */
@Service
public class EmailServiceImpl implements IInboxService {
    @Autowired
    private ITeacherService teacherService;
    @Autowired
    private IStudentService studentService;
    @Autowired
    private EmailMapper emailMapper;
    @Autowired
    private IStudentClassService studentClassService;

    @Override
    public JsonResult insertEmail(String body, String subject, String teacherId, String studentId, String address, String fileName) throws Exception {
        SequenceUtil.isBlank(subject, "subject为空！");
        SequenceUtil.isBlank(teacherId, "teacherId为空！");
        SequenceUtil.isBlank(studentId, "studentId为空！");
        SequenceUtil.isBlank(address, "address为空！");
        JsonResult teaRe = teacherService.queryRoleById(teacherId);
        if (teaRe.isSuccess() && null != teaRe.getData()) {
            Teacher teacher = (Teacher) teaRe.getData();
            JsonResult stuRe = studentService.queryRoleById(studentId);
            if (stuRe.isSuccess() && null != stuRe.getData()) {
                Student student = (Student) stuRe.getData();
                String studentEmail = student.getEmail();
                String teacherEmail = teacher.getEmail();
                if (address.equals(studentEmail) || address.equals(teacherEmail)) {
                    Email email = new Email();
                    email.setId(SequenceUtil.getSequence());
                    email.setCreateTime(DateUtil.getCurDate(DateUtil.DEFAULT_DATE_FORMAT));
                    email.setStudentId(studentId);
                    email.setBody(body);
                    email.setSubject(subject);
                    email.setValid(true);
                    email.setReaded(false);
                    email.setAddress(address);
                    email.setTeacherId(teacherId);
                    email.setType(Contants.Email.EMAIL_SEND_TYPE);
                    email.setImportant(Contants.Email.EMAIL_NOT_IMPORTANT);
                    email.setFileName(fileName);
                    emailMapper.insertEmail(email);
                } else {
                    return JsonResult.failedInstance("没有此邮箱");
                }

            }
        }
        return JsonResult.failedInstance("没有此人");
    }

    @Override
    public JsonResult makeReaded(String id) throws Exception {
        emailMapper.updateReaded(id);
        return JsonResult.success();
    }

    @Override
    public JsonResult deleteEmail(String id) throws Exception {
        emailMapper.deleteById(id);
        return JsonResult.success();
    }

    @Override
    public JsonResult findEmail(Email email) throws Exception {
        Assert.notNull(email, "查询条件不能为空");
        EmailVoForRead emailVoForRead = new EmailVoForRead();
        List<Email> emailList = emailMapper.selectByCondition(email);
        if (CollectionUtils.isEmpty(emailList)) {
            emailVoForRead = null;
        } else {
            List<EmailVo> readedEmailVos = new ArrayList<>();
            List<EmailVo> unReadEmailVos = new ArrayList<>();
            List<EmailVo> sendEmailVos = new ArrayList<>();
            List<EmailVo> deletedEmails = new ArrayList<>();
            List<EmailVo> importantEmails = new ArrayList<>();
            for (Email e : emailList) {
                EmailVo emailVo = new EmailVo();
                emailVo.setStudentId(e.getStudentId());
                emailVo.setTeacherId(e.getTeacherId());
                emailVo.setAddress(e.getAddress());
                emailVo.setReaded(e.isReaded());
                emailVo.setId(e.getId());
                emailVo.setSubject(e.getSubject());
                emailVo.setBody(e.getBody());
                emailVo.setCreateTime(e.getCreateTime());
                emailVo.setType(e.getType());
                emailVo.setImportant(e.isImportant());
                emailVo.setFileName(e.getFileName());
                JsonResult result = teacherService.queryRoleById(e.getTeacherId());
                if (result.isSuccess() && null != result.getData()) {
                    Teacher teacher = (Teacher) result.getData();
                    emailVo.setTeacherName(teacher.getName());
                }
                StudentClass studentClass = new StudentClass();
                studentClass.setStudentId(e.getStudentId());
                JsonResult stRe = studentClassService.selectRelationship(null, null, e.getStudentId(), 0, 1, null, null);
                if (stRe.isSuccess() && null != stRe.getData()) {
                    List<StudentClass> studentClasses = (List<StudentClass>) stRe.getData();
                    StudentClass student = studentClasses.get(0);
                    emailVo.setStudentName(student.getStudentName());
                    emailVo.setStudentNo(student.getStudentNo());
                }

                if (!e.isValid()) {
                    deletedEmails.add(emailVo);
                    continue;
                }
                if (e.isReaded() && e.getType() == Contants.Email.EMAIL_RECEIVED_TYPE && e.isValid()) {
                    if (e.isImportant()) {
                        importantEmails.add(emailVo);
                    }
                    readedEmailVos.add(emailVo);
                    continue;
                }
                if (!e.isReaded() && e.getType() == Contants.Email.EMAIL_RECEIVED_TYPE && e.isValid()) {
                    if (e.isImportant()) {
                        importantEmails.add(emailVo);
                    }
                    unReadEmailVos.add(emailVo);
                    continue;
                }
                if (!e.isReaded() && e.getType() == Contants.Email.EMAIL_SEND_TYPE && e.isValid()) {
                    sendEmailVos.add(emailVo);
                    continue;
                }


            }
            emailVoForRead.setReadedEmails(readedEmailVos);
            emailVoForRead.setUnReadEmails(unReadEmailVos);
        }
        return JsonResult.jsonSuccessData(emailVoForRead);
    }

    @Override
    public JsonResult selectUnreadCount(Email email) throws Exception {
        Assert.notNull(email);
        int count = emailMapper.selectUnreadCount(email);
        return JsonResult.jsonSuccessData(count);
    }
}
