package com.dfire.grade.manager.controller;

import com.dfire.grade.manager.Contants;
import com.dfire.grade.manager.bean.SignBean;
import com.dfire.grade.manager.service.IClassService;
import com.dfire.grade.manager.service.ITeacherService;
import com.dfire.grade.manager.vo.ClassIncludeSchoolTime;
import com.dfire.grade.manager.vo.JsonResult;
import com.dfire.grade.manager.vo.form.ClassForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
                              @RequestBody(required = false) List<ClassForm> classFormList) throws Exception {
        if (request.getMethod().equals(Contants.Http.METHOD_POST)) {
            if (null != classFormList) {
                SignBean signBean = (SignBean) request.getSession().getAttribute(Contants.TEACHER_KEY);
                String teacherId = signBean.getId();
                JsonResult teaRe = teacherService.queryRoleById(teacherId);
                if (teaRe.isSuccess() && null != teaRe.getData()) {
                    List<ClassIncludeSchoolTime> classIncludeSchoolTimes = new ArrayList<>();
                    for (ClassForm classForm : classFormList) {
                        Map<String, Integer> schoolTimes = classForm.getSchoolTimes();
                        if (!schoolTimes.isEmpty()) {
                            ClassIncludeSchoolTime classIncludeSchoolTime = new ClassIncludeSchoolTime();
                            classIncludeSchoolTime.setCredit(classForm.getCredit());
                            classIncludeSchoolTime.setName(classForm.getName());
                            classIncludeSchoolTime.setPeriod(classForm.getPeriod());
                            classIncludeSchoolTime.setTeacherId(teacherId);
                            classIncludeSchoolTime.setSchoolTimes(classForm.getSchoolTimes());
                            classIncludeSchoolTime.setFrequency(schoolTimes.size());
                            classIncludeSchoolTime.setFrequencyUnit(classForm.getFrequencyUnit());
                            classIncludeSchoolTimes.add(classIncludeSchoolTime);
                        } else {
                            model.addAttribute("message", "没有上课时间，放弃创建");
                        }
                    }
                    JsonResult result = classService.insertClass(classIncludeSchoolTimes);
                    if (result.isSuccess()) {
                        JsonResult classRe = classService.selectAllClassByTeacherIdAndPage(teacherId, 0, 10, null, null);
                        if (classRe.isSuccess() && null != classRe.getData()) {
                            model.addAttribute("classList", classRe.getData());
                            return "class/class_list";
                        }
                        model.addAttribute("message", "创建失败！");
                    }
                } else {
                    model.addAttribute("message", "没有权限！");
                }
            } else {
                model.addAttribute("message", "参数为空！");
            }
        }
        return "class/create_class";
    }

    @RequestMapping(value = "/detail", method = {RequestMethod.POST})
    @ResponseStatus(HttpStatus.OK)
    public String getClassIncludeDetail(Model model, @RequestParam(value = "class_id", required = true) String classId) throws Exception {
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
                                  @RequestParam(value = "start_time", required = false) Date startTime,
                                  @RequestParam(value = "end_time", required = false) Date endTime,
                                  @RequestParam(value = "index", required = false) Integer index,
                                  @RequestParam(value = "page_size", required = false) Integer pageSize) throws Exception {
        if (null == index) {
            index = 0;
        }
        if (null == pageSize) {
            pageSize = 10;
        }
        SignBean signBean = (SignBean) request.getSession().getAttribute(Contants.TEACHER_KEY);
        String teacherId = signBean.getId();
        JsonResult classes = classService.selectAllClassByTeacherIdAndPage(teacherId, index, pageSize, startTime, endTime);
        if (classes.isSuccess() && null != classes.getData()) {
            model.addAttribute("classList", classes.getData());
        } else {
            model.addAttribute("message", "没有查出数据");
        }
        return "class/class_list";
    }

    @RequestMapping(value = "/student", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseStatus(HttpStatus.OK)
    public String getStudentClass(HttpServletRequest request, Model model,
                                  @RequestParam(value = "start_time", required = false) Date startTime,
                                  @RequestParam(value = "end_time", required = false) Date endTime,
                                  @RequestParam(value = "index", required = true) Integer index,
                                  @RequestParam(value = "page_size", required = true) Integer pageSize) throws Exception {
        if (null == index) {
            index = 0;
        }
        if (null == pageSize) {
            pageSize = 10;
        }
        SignBean signBean = (SignBean) request.getSession().getAttribute(Contants.TEACHER_KEY);
        String student = signBean.getId();
        JsonResult classes = classService.selectAllClassByStudentIDAndPage(student, index, pageSize, startTime, endTime);
        if (classes.isSuccess() && null != classes.getData()) {
            model.addAttribute("classList", classes.getData());
        } else {
            model.addAttribute("message", "没有查出数据");
        }
        return "class/class_list";
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public JsonResult deleteClass(HttpServletRequest request, Model model, @RequestParam(value = "class_id", required = false) String classId) throws Exception {
        SignBean signBean = (SignBean) request.getSession().getAttribute(Contants.TEACHER_KEY);
        String teacherId = signBean.getId();
        JsonResult teaRe = teacherService.queryRoleById(teacherId);
        if (teaRe.isSuccess() && null != teaRe.getData()) {
            classService.deleteClassByClassId(classId);
            return JsonResult.jsonSuccessMes(Contants.Message.SUCCESS_REQUEST);
        }
        return JsonResult.failedInstance(Contants.Message.NOT_PERMISSION);
    }
}
