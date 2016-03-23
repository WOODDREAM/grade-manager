package com.dfire.grade.manager.controller;

import com.dfire.grade.manager.utils.RedisUtil;
import com.dfire.grade.manager.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * User:huangtao
 * Date:2016-03-23
 * description：
 */
@RequestMapping("/class")
public class ClassController {
    @Autowired
    private RedisUtil redisUtil;

    public JsonResult createClass(HttpRequest request,
                                  @RequestParam(value = "", required = true) String className) {
            request.getHeaders();
        return null;
    }
}
