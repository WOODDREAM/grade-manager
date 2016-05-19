package com.dfire.grade.manager.controller;

import com.dfire.grade.manager.Contants;
import com.dfire.grade.manager.bean.*;
import com.dfire.grade.manager.mapper.StudentClassMapper;
import com.dfire.grade.manager.service.*;
import com.dfire.grade.manager.utils.SmsUtil;
import com.dfire.grade.manager.vo.ClassVo2;
import com.dfire.grade.manager.vo.JsonResult;
import com.dfire.grade.manager.vo.RelationshipVo;
import com.dfire.grade.manager.vo.Reliation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User:huangtao
 * Date:2016-04-14
 * description：
 */
@RequestMapping("/relationship")
@Controller
public class StudentClassController extends BaseController {
    @Autowired
    private IStudentClassService studentClassService;
    @Autowired
    private IClassStartService classStartService;
    @Autowired
    private ITeacherService teacherService;
    @Autowired
    private IStudentService studentService;
    @Autowired
    private IClassService classService;
    @Autowired
    private StudentClassMapper studentClassMapper;
    @Autowired
    private SmsUtil smsUtil;
    private String AGREED = "课程码：%s。课程名： %s。\n你已被同意加入此课程。。如有疑问，请联系教师： %s\n";
    private String DELETE = "课程码：%s。课程名： %s。\n你已被教师：%s 从此课程移除。如有疑问，请联系教师。\n";
    private String OUT = "课程码：%s。课程名： %s。\n学生：%s 已退出此课程。";

    @RequestMapping(value = "/agree", method = {RequestMethod.POST})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public JsonResult agreeJoin(HttpServletRequest request, String relationshipId) throws Exception {
        SignBean signBean = (SignBean) request.getSession().getAttribute(Contants.TEACHER_KEY);
        String teacherId = signBean.getId();
        JsonResult teaRe = teacherService.queryRoleById(teacherId);
        if (teaRe.isSuccess() && null != teaRe.getData()) {
            Teacher teacher = (Teacher) teaRe.getData();
            JsonResult agree = studentClassService.updateAgree(relationshipId);
            if (agree.isSuccess() && null != agree.getData()) {
                StudentClass studentClass = (StudentClass) agree.getData();
                JsonResult result = classService.selectClassById(studentClass.getClassId());
                if (result.isSuccess() && null != result.getData()) {
                    ClassVo2 classVo2 = (ClassVo2) result.getData();
                    String message = String.format(AGREED, studentClass.getClassNo(), classVo2.getName(), teacher.getName());
                    try {
                        smsUtil.sendSMS(studentClass.getMobile(), message);
                    } catch (Exception e) {
                    }
                }
                return JsonResult.jsonSuccessMes(Contants.Message.SUCCESS_REQUEST);
            } else {
                return agree;
            }
        } else {
            return JsonResult.failedInstance(Contants.Message.NOT_PERMISSION);
        }
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public JsonResult deleteJoin(HttpServletRequest request, @RequestParam(value = "relationshipId", required = true) String relationshipId,
                                 @RequestParam(value = "type", required = false) Integer type) throws Exception {

        JsonResult rs = null;
        SignBean signBean = null;
        if (null != type && type.equals(1)) {
            signBean = (SignBean) request.getSession().getAttribute(Contants.STUDENT_KEY);
            String studentId = signBean.getId();
            rs = studentService.queryRoleById(studentId);
        } else {
            signBean = (SignBean) request.getSession().getAttribute(Contants.TEACHER_KEY);
            String teacherId = signBean.getId();
            rs = teacherService.queryRoleById(teacherId);
        }
        if (null != signBean && null != rs && rs.isSuccess() && null != rs.getData()) {
            StudentClass studentClass = studentClassMapper.selectByShipId(relationshipId);
            if (null != studentClass) {
                JsonResult result = classService.selectClassById(studentClass.getClassId());
                if (result.isSuccess() && null != result.getData()) {
                    StudentClass s = new StudentClass();
                    s.setRelationshipId(relationshipId);
                    ClassVo2 classVo2 = (ClassVo2) result.getData();
                    String message = String.format(DELETE, studentClass.getClassNo(), classVo2.getName(), signBean.getName());
                    try {
                        smsUtil.sendSMS(studentClass.getMobile(), message);
                    } catch (Exception e) {
                    }
                    return studentClassService.deleteById(s);
                } else {
                    return JsonResult.failedInstance("没有找到相关课程，请删除！");
                }
            } else {
                return JsonResult.failedInstance("没有找到相关开课记录，请删除！");
            }

        } else {
            return JsonResult.failedInstance(Contants.Message.NOT_PERMISSION);
        }
    }

    @RequestMapping(value = "/out", method = {RequestMethod.POST})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public JsonResult outJoin(HttpServletRequest request,
                              @RequestParam(value = "relationshipId", required = false) String relationshipId,
                              @RequestParam(value = "classId", required = false) String classId) throws Exception {
        SignBean signBean = (SignBean) request.getSession().getAttribute(Contants.STUDENT_KEY);
        String studentId = signBean.getId();
        JsonResult stuRe = studentService.queryRoleById(studentId);
        if (stuRe.isSuccess() && null != stuRe.getData()) {
            Student student = (Student) stuRe.getData();
            StudentClass studentClass = null;
            if (!StringUtils.isEmpty(relationshipId)) {
                studentClass = studentClassMapper.selectByShipId(relationshipId);
            }
            if (null == studentClass) {
                JsonResult result = studentClassService.selectIfJoinedById(student.getMobile(), classId);
                if (result.isSuccess() && null != result.getData()) {
                    studentClass = (StudentClass) result.getData();
                }
            }
            if (null != studentClass) {
                JsonResult rs = classService.selectClassById(studentClass.getClassId());
                if (rs.isSuccess() && null != rs.getData()) {
                    ClassVo2 classVo2 = (ClassVo2) rs.getData();
                    String message = String.format(OUT, studentClass.getClassNo(), classVo2.getName(), signBean.getName());
                    try {
                        smsUtil.sendSMS(studentClass.getMobile(), message);
                    } catch (Exception e) {
                    }
                    return studentClassService.deleteById(studentClass);
                } else {
                    return JsonResult.failedInstance("没有找到相关课程，请删除！");
                }
            } else {
                return JsonResult.failedInstance("没有找到相关开课记录，请删除！");
            }
        } else {
            return JsonResult.failedInstance(Contants.Message.NOT_PERMISSION);
        }
    }


    @RequestMapping(value = "/teacher", method = {RequestMethod.POST})
    @ResponseStatus(HttpStatus.OK)
    public String shipByTeacher(HttpServletRequest request, Model model) throws Exception {
        SignBean signBean = (SignBean) request.getSession().getAttribute(Contants.TEACHER_KEY);
        String teacherId = signBean.getId();
        JsonResult teaRe = teacherService.queryRoleById(teacherId);
        if (teaRe.isSuccess() && null != teaRe.getData()) {
            JsonResult jsonResult = classStartService.selectByTeacher(teacherId);
            if (jsonResult.isSuccess() && null != jsonResult.getData()) {
                List<ClassStart> classStarts = (List<ClassStart>) jsonResult.getData();
                List<String> classIdList = new ArrayList<>();
                for (ClassStart classStart : classStarts) {
                    classIdList.add(classStart.getClassId());
                }
                if (!CollectionUtils.isEmpty(classIdList)) {
                    JsonResult result = classService.selectClassBatch(classIdList);
                    if (result.isSuccess() && null != result.getData()) {
                        List<ClassVo2> classVo2s = (List<ClassVo2>) result.getData();
                        for (int i = 0; i < classVo2s.size(); i++) {
                            classVo2s.get(i).setState(Contants.ClassState.STARTING);
                        }
                        model.addAttribute("classList", classVo2s);
                    }
                }
            }
        }
        return "class/class_list";
    }

    @RequestMapping(value = "/main", method = {RequestMethod.POST})
    @ResponseStatus(HttpStatus.OK)
    public String shipByStudent(HttpServletRequest request, Model model,
                                @RequestParam(value = "startTime", required = false) Date startTime,
                                @RequestParam(value = "end_time", required = false) Date endTime,
                                @RequestParam(value = "pageSize", required = false, defaultValue = "1000") Integer pageSize,
                                @RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex) throws Exception {
        SignBean signBean = (SignBean) request.getSession().getAttribute(Contants.STUDENT_KEY);
        String teacherId = null;
        String studentId = null;
        JsonResult result = null;
        int roleType = 0;
        if (null == signBean) {
            signBean = (SignBean) request.getSession().getAttribute(Contants.TEACHER_KEY);
            teacherId = signBean.getId();
            result = teacherService.queryRoleById(teacherId);
            roleType = 2;
        } else {
            studentId = signBean.getId();
            result = studentService.queryRoleById(studentId);
            roleType = 1;
        }
        if (result.isSuccess() && null != result.getData()) {
            int index = ((pageIndex - 1) * 10) - 1;
            if (-1 == index) {
                index = 0;
            }
            JsonResult resultS = studentClassService.selectRelationship(teacherId, studentId, index, pageSize, startTime, endTime);
            if (resultS.isSuccess() && null != resultS.getData()) {
                RelationshipVo relationshipVo = (RelationshipVo) resultS.getData();
                List<Reliation> agreeClass = relationshipVo.getAgreeClass();
                List<Reliation> notAgreeClass = relationshipVo.getNotAgreeClass();
                model.addAttribute("agreeClass", agreeClass);
                model.addAttribute("notAgreeClass", notAgreeClass);
            }
        } else {
            model.addAttribute("message", "非学生！请切换身份登录。");
        }
        model.addAttribute("roleType", roleType);
        return "ship/list";
    }
}
