package com.dfire.grade.manager.controller;

import com.dfire.grade.manager.Contants;
import com.dfire.grade.manager.bean.SignBean;
import com.dfire.grade.manager.service.IClassService;
import com.dfire.grade.manager.utils.RedisUtil;
import com.dfire.grade.manager.vo.ClassIncludeSchoolTime;
import com.dfire.grade.manager.vo.JsonResult;
import com.dfire.grade.manager.vo.form.ClassForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RestController
public class ClassController extends BaseController {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private IClassService classService;

    @RequestMapping(value = "/create", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public JsonResult createClass(HttpServletRequest request,
                                  @RequestBody(required = false) List<ClassForm> classFormList) throws Exception {
        SignBean signBean = (SignBean) redisUtil.getValue(Contants.RedisContent.TEACHER_SIGN_CACHE_BY_ID + request.getHeader("UID"), SignBean.class);
        String teacherId = signBean.getId();
        List<ClassIncludeSchoolTime> classIncludeSchoolTimes = new ArrayList<>();
        for (ClassForm classForm : classFormList) {
            ClassIncludeSchoolTime classIncludeSchoolTime = new ClassIncludeSchoolTime();
            classIncludeSchoolTime.setCredit(classForm.getCredit());
            classIncludeSchoolTime.setName(classForm.getName());
            classIncludeSchoolTime.setPeriod(classForm.getPeriod());
            classIncludeSchoolTime.setTeacherId(teacherId);
            classIncludeSchoolTime.setSchoolTimes(classForm.getSchoolTimes());
            classIncludeSchoolTime.setFrequency(classForm.getFrequency());
            classIncludeSchoolTime.setFrequencyUnit(classForm.getFrequencyUnit());
            classIncludeSchoolTimes.add(classIncludeSchoolTime);

        }
        return classService.insertClass(classIncludeSchoolTimes);
    }

    @RequestMapping(value = "/detail", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public JsonResult getClassIncludeDetail(@RequestParam(value = "class_id", required = true) String classId) throws Exception {
        JsonResult classes = classService.selectClassIncludeDetailById(classId);
        if (classes.isSuccess() && null != classes.getData()) {
            return classes;
        } else {
            return JsonResult.failedInstance(Contants.Message.ERROR_NO_CLASS);
        }
    }

    @RequestMapping(value = "/teacher", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public JsonResult getTeacherClass(HttpServletRequest request,
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
        SignBean signBean = (SignBean) redisUtil.getValue(Contants.RedisContent.TEACHER_SIGN_CACHE_BY_ID + request.getHeader("UID"), SignBean.class);
        String teacherId = signBean.getId();
        JsonResult classes = classService.selectAllClassByTeacherIdAndPage(teacherId, index, pageSize, startTime, endTime);
        if (classes.isSuccess() && null != classes.getData()) {
            return classes;
        } else {
            return JsonResult.failedInstance(Contants.Message.ERROR_NO_CLASS);
        }
    }

    @RequestMapping(value = "/student", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public JsonResult getStudentClass(HttpServletRequest request,
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
        SignBean signBean = (SignBean) redisUtil.getValue(Contants.RedisContent.STUDENT_SIGN_CACHE_BY_ID + request.getHeader("UID"), SignBean.class);
        String student = signBean.getId();
        JsonResult classes = classService.selectAllClassByStudentIDAndPage(student, index, pageSize, startTime, endTime);
        if (classes.isSuccess() && null != classes.getData()) {
            return classes;
        } else {
            return JsonResult.failedInstance(Contants.Message.ERROR_NO_CLASS);
        }
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public JsonResult deleteClass(@RequestParam(value = "class_id", required = false) String classId) throws Exception {
        classService.deleteClassByClassId(classId);
        return JsonResult.jsonSuccessMes(Contants.Message.SUCCESS_REQUEST);
    }
}
