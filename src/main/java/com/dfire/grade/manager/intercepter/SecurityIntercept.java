package com.dfire.grade.manager.intercepter;

import com.dfire.grade.manager.Contants;
import com.dfire.grade.manager.bean.SignBean;
import com.dfire.grade.manager.utils.RedisUtil;
import com.dfire.grade.manager.vo.JsonResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User:huangtao
 * Date:2016-04-12
 * description：
 */
@Component
public class SecurityIntercept implements HandlerInterceptor {
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse response, Object o) throws Exception {
        SignBean signBean = (SignBean) redisUtil.getValue(Contants.RedisContent.STUDENT_SIGN_CACHE_BY_ID + httpServletRequest.getHeader(Contants.UID), SignBean.class);
        if (null == signBean) {
            signBean = (SignBean) redisUtil.getValue(Contants.RedisContent.TEACHER_SIGN_CACHE_BY_ID + httpServletRequest.getHeader(Contants.UID), SignBean.class);
            if (null != signBean) {
                redisUtil.setValuePre(Contants.RedisContent.TEACHER_SIGN_CACHE_BY_ID + signBean.getId(), signBean, Contants.RedisContent.USERINFO_EXPIRE_TIME, Contants.RedisContent.MINUTES_UNIT);
            }
        } else {
            redisUtil.setValuePre(Contants.RedisContent.STUDENT_SIGN_CACHE_BY_ID + signBean.getId(), signBean, Contants.RedisContent.USERINFO_EXPIRE_TIME, Contants.RedisContent.MINUTES_UNIT);
        }
        if (null == signBean || !signBean.isSign()) {
            JsonResult jsonResult = new JsonResult();
            jsonResult.setCode("0");
            jsonResult.setMessage(Contants.Message.ERROR_PLEASE_SIGN_IN);
            response.setCharacterEncoding("UTF-8");
            response.addHeader("Content-Type", "application/json");
            response.containsHeader(String.valueOf(jsonResult));
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonStr = objectMapper.writeValueAsString(jsonResult);
            response.getWriter().write(jsonStr);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
