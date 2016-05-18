package com.dfire.grade.manager.controller;

import com.dfire.grade.manager.Contants;
import com.dfire.grade.manager.bean.SignBean;
import com.dfire.grade.manager.service.IClassService;
import com.dfire.grade.manager.service.ITeacherService;
import com.dfire.grade.manager.utils.DateUtil;
import com.dfire.grade.manager.utils.SequenceUtil;
import com.dfire.grade.manager.vo.ClassIncludeSchoolTime;
import com.dfire.grade.manager.vo.JsonResult;
import com.dfire.grade.manager.vo.Schedule;
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
 * Date:2016-03-23
 * description：
 */
@RequestMapping("/class")
@Controller
public class ClassController extends BaseController {
    @Autowired
    private IClassService classService;
    @Autowired
    private ITeacherService teacherService;

    @RequestMapping(value = "/create", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseStatus(HttpStatus.OK)
    public String createClass(HttpServletRequest request, Model model,
                              @RequestParam(value = "frequencyUnit", required = false) String frequencyUnit,
                              @RequestParam(value = "name", required = false) String name,
                              @RequestParam(value = "period", required = false) Double period,
                              @RequestParam(value = "credit", required = false) Double credit,
                              @RequestParam(value = "startTime", required = false) String startTime,
                              @RequestParam(value = "endTime", required = false) String endTime,
                              @RequestParam(value = "schoolTimes", required = false) String schoolTimes) throws Exception {
        if (request.getMethod().equals(Contants.Http.METHOD_POST)) {
            SignBean signBean = (SignBean) request.getSession().getAttribute(Contants.TEACHER_KEY);
            String teacherId = signBean.getId();
            JsonResult teaRe = teacherService.queryRoleById(teacherId);
            if (teaRe.isSuccess() && null != teaRe.getData()) {
                List<ClassIncludeSchoolTime> classIncludeSchoolTimes = new ArrayList<>();
                List<Schedule> sch = SequenceUtil.stringToSchedule(schoolTimes);
                if (!CollectionUtils.isEmpty(sch)) {
                    ClassIncludeSchoolTime classIncludeSchoolTime = new ClassIncludeSchoolTime();
                    classIncludeSchoolTime.setCredit(credit);
                    classIncludeSchoolTime.setName(name);
                    classIncludeSchoolTime.setPeriod(period);
                    classIncludeSchoolTime.setTeacherId(teacherId);
                    classIncludeSchoolTime.setSchoolTimes(sch);
                    classIncludeSchoolTime.setFrequency(sch.size());
                    classIncludeSchoolTime.setFrequencyUnit(frequencyUnit);
                    classIncludeSchoolTime.setEndTime(DateUtil.parseDate(endTime, DateUtil.DEFAULT_MOUTH_DAY_YEAR));
                    classIncludeSchoolTime.setStartTime(DateUtil.parseDate(startTime, DateUtil.DEFAULT_MOUTH_DAY_YEAR));
                    classIncludeSchoolTimes.add(classIncludeSchoolTime);
                    JsonResult result = classService.insertClass(classIncludeSchoolTimes);
                    if (result.isSuccess()) {
                        JsonResult classRe = classService.selectAllClassByTeacherIdAndPage(teacherId, 0, 10, null, null);
                        if (classRe.isSuccess() && null != classRe.getData()) {
                            model.addAttribute("classList", classRe.getData());
                            model.addAttribute("roleType", 2);
                            return "class/class_list";
                        }
                        model.addAttribute("message", "创建失败！");
                    }
                } else {
                    model.addAttribute("message", "没有上课时间，放弃创建");
                }
            } else {
                model.addAttribute("message", "没有权限！");
            }
        }
        return "class/create_class";
    }

    @RequestMapping(value = "/detail", method = {RequestMethod.POST})
    @ResponseStatus(HttpStatus.OK)
    public String getClassIncludeDetail(Model model, @RequestParam(value = "classId", required = true) String classId) throws Exception {
        JsonResult classes = classService.selectClassIncludeDetailById(classId);
        if (classes.isSuccess() && null != classes.getData()) {
            model.addAttribute("classDetail", classes.getData());
        } else {
            model.addAttribute("message", "请求失败");
        }
        return "class/class_detail";
    }

    @RequestMapping(value = "/teacher", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseStatus(HttpStatus.OK)
    public String getTeacherClass(HttpServletRequest request, Model model,
                                  @RequestParam(value = "startTime", required = false) Date startTime,
                                  @RequestParam(value = "endTime", required = false) Date endTime,
                                  @RequestParam(value = "pageIndex", required = false) Integer pageIndex,
                                  @RequestParam(value = "pageSize", required = false) Integer pageSize) throws Exception {
        if (null == pageIndex) {
            pageIndex = 1;
        }
        if (null == pageSize) {
            pageSize = 1000;
        }
        int index = ((pageIndex - 1) * 10) - 1;
        if (-1 == index) {
            index = 0;
        }
        SignBean signBean = (SignBean) request.getSession().getAttribute(Contants.TEACHER_KEY);
        String teacherId = signBean.getId();
        JsonResult classes = classService.selectAllClassByTeacherIdAndPage(teacherId, index, pageSize, startTime, endTime);
        if (classes.isSuccess() && null != classes.getData()) {
            model.addAttribute("classList", classes.getData());
        } else {
            model.addAttribute("message", "没有查出数据");
        }
        model.addAttribute("roleType", 2);
        return "class/class_list";
    }

    @RequestMapping(value = "/student", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseStatus(HttpStatus.OK)
    public String getStudentClass(HttpServletRequest request, Model model,
                                  @RequestParam(value = "startTime", required = false) Date startTime,
                                  @RequestParam(value = "end_time", required = false) Date endTime,
                                  @RequestParam(value = "pageIndex", required = false) Integer pageIndex,
                                  @RequestParam(value = "pageSize", required = false) Integer pageSize) throws Exception {
        if (null == pageIndex) {
            pageIndex = 1;
        }
        if (null == pageSize) {
            pageSize = 1000;
        }
        SignBean signBean = (SignBean) request.getSession().getAttribute(Contants.TEACHER_KEY);
        String student = signBean.getId();
        int index = ((pageIndex - 1) * 10) - 1;
        if (-1 == index) {
            index = 0;
        }
        JsonResult classes = classService.selectAllClassByStudentIDAndPage(student, index, pageSize, startTime, endTime);
        if (classes.isSuccess() && null != classes.getData()) {
            model.addAttribute("classList", classes.getData());
        } else {
            model.addAttribute("message", "没有查出数据");
        }
        model.addAttribute("roleType", 1);
        return "class/class_list";
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public JsonResult deleteClass(HttpServletRequest request, Model model, @RequestParam(value = "classId", required = true) String classId) throws Exception {
        SignBean signBean = (SignBean) request.getSession().getAttribute(Contants.TEACHER_KEY);
        String teacherId = signBean.getId();
        JsonResult teaRe = teacherService.queryRoleById(teacherId);
        if (teaRe.isSuccess() && null != teaRe.getData()) {
            classService.deleteClassByClassId(classId);
            return JsonResult.jsonSuccessMes(Contants.Message.SUCCESS_REQUEST);
        }
        return JsonResult.failedInstance(Contants.Message.NOT_PERMISSION);
    }

    @RequestMapping(value = "/update", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseStatus(HttpStatus.OK)
    public String updateClass(HttpServletRequest request, Model model,
                              @RequestParam(value = "classId", required = true) String classId,
                              @RequestParam(value = "name", required = false) String name,
                              @RequestParam(value = "period", required = false) Double period,
                              @RequestParam(value = "credit", required = false) Double credit,
                              @RequestParam(value = "startTime", required = false) String startTime,
                              @RequestParam(value = "endTime", required = false) String endTime,
                              @RequestParam(value = "schoolTimes", required = false) String schoolTimes) throws Exception {
        if (!StringUtils.isEmpty(classId)) {
            if (request.getMethod().equals(Contants.Http.METHOD_POST)) {
                SignBean signBean = (SignBean) request.getSession().getAttribute(Contants.TEACHER_KEY);
                String teacherId = signBean.getId();
                JsonResult teaRe = teacherService.queryRoleById(teacherId);
                if (teaRe.isSuccess() && null != teaRe.getData()) {
                    List<ClassIncludeSchoolTime> classIncludeSchoolTimes = new ArrayList<>();
                    List<Schedule> sch = SequenceUtil.stringToSchedule(schoolTimes);
                    if (!CollectionUtils.isEmpty(sch) && !StringUtils.isEmpty(classId)) {
                        ClassIncludeSchoolTime classIncludeSchoolTime = new ClassIncludeSchoolTime();
                        classIncludeSchoolTime.setCredit(credit);
                        classIncludeSchoolTime.setName(name);
                        classIncludeSchoolTime.setPeriod(period);
                        classIncludeSchoolTime.setTeacherId(teacherId);
                        classIncludeSchoolTime.setSchoolTimes(sch);
                        classIncludeSchoolTime.setClassId(classId);
                        classIncludeSchoolTime.setFrequency(sch.size());
                        if (endTime.contains("/")) {
                            classIncludeSchoolTime.setEndTime(DateUtil.parseDate(endTime, DateUtil.DEFAULT_MOUTH_DAY_YEAR));
                        } else {
                            classIncludeSchoolTime.setEndTime(DateUtil.parseDate(endTime, DateUtil.DEFAULT_DATE_FORMAT));
                        }
                        if (startTime.contains("/")) {
                            classIncludeSchoolTime.setStartTime(DateUtil.parseDate(startTime, DateUtil.DEFAULT_MOUTH_DAY_YEAR));
                        } else {
                            classIncludeSchoolTime.setStartTime(DateUtil.parseDate(startTime, DateUtil.DEFAULT_DATE_FORMAT));
                        }
                        classIncludeSchoolTimes.add(classIncludeSchoolTime);
                        JsonResult result = classService.upDateClassByClassId(classIncludeSchoolTimes);
                        if (result.isSuccess()) {
                            JsonResult classRe = classService.selectAllClassByTeacherIdAndPage(teacherId, 0, 10, null, null);
                            if (classRe.isSuccess() && null != classRe.getData()) {
                                model.addAttribute("classList", classRe.getData());
                                model.addAttribute("roleType", 2);
                                return "class/class_list";
                            }
                            model.addAttribute("message", "创建失败！");
                        }
                    } else {
                        model.addAttribute("message", "没有上课时间，放弃更新！");
                    }
                } else {
                    model.addAttribute("message", "没有权限！");
                }
            }
            JsonResult result = classService.selectClassIncludeDetailById(classId);
            if (result.isSuccess() && null != result.getData()) {
                model.addAttribute("classDetail", result.getData());
            }
        } else {
            model.addAttribute("message", "参数错误 classId不能为空！");
        }
        return "class/update";
    }
}
