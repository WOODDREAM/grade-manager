package com.dfire.grade.manager.controller;

import com.dfire.grade.manager.bean.UserInfoCache;
import com.dfire.grade.manager.utils.RedisUtil;
import com.dfire.grade.manager.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * User:huangtao
 * Date:2016-03-23
 * description：
 */
@RequestMapping("/class")
public class ClassController {
    @Autowired
    private RedisUtil redisUtil;

    public JsonResult createClass(HttpServletRequest request,
                                  @RequestParam(value = "class_name", required = true) String className,
                                  @RequestParam(value = "credit", required = true) String credit,
                                  @RequestParam(value = "period", required = true) Double period) throws IOException {
        UserInfoCache userInfoCache = (UserInfoCache) redisUtil.getValue(request.getHeader("CID"), UserInfoCache.class);
        String teacherId = userInfoCache.getId();

        return null;
    }
}
