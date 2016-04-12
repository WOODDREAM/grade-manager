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
import java.util.List;

/**
 * User:huangtao
 * Date:2016-03-23
 * description：
 */
@RequestMapping("/class")
@RestController
public class ClassController {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private IClassService classService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public JsonResult createClass(HttpServletRequest request,
                                  @RequestBody(required = false) List<ClassForm> classFormList) throws Exception {
        SignBean signBean = (SignBean) redisUtil.getValue(Contants.RedisContent.TEACHER_CACHE_BY_ID + request.getHeader("UID"), SignBean.class);
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
        classService.insertClass(classIncludeSchoolTimes);
        return null;
    }
}
