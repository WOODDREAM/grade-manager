package com.dfire.grade.manager.controller;

import com.dfire.grade.manager.bean.UserInfoCache;
import com.dfire.grade.manager.service.IClassService;
import com.dfire.grade.manager.utils.RedisUtil;
import com.dfire.grade.manager.vo.ClassIncludeSchoolTime;
import com.dfire.grade.manager.vo.JsonResult;
import com.dfire.grade.manager.vo.form.ClassForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * User:huangtao
 * Date:2016-03-23
 * description：
 */
@RequestMapping("/class")
public class ClassController {
    @Autowired
    private RedisUtil redisUtil;
    private IClassService classService;

    public JsonResult createClass(HttpServletRequest request,
                                  @RequestParam("class_list")List<ClassForm> classFormList) throws IOException {
        UserInfoCache userInfoCache = (UserInfoCache) redisUtil.getValue(request.getHeader("CID"), UserInfoCache.class);
        String teacherId = userInfoCache.getId();
        List<ClassIncludeSchoolTime> classIncludeSchoolTime = new ArrayList<>();
        classService.insertClass();
        return null;
    }
}
