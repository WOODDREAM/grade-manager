package com.dfire.grade.manager.controller;

import com.dfire.grade.manager.Contants;
import com.dfire.grade.manager.exception.BusinessException;
import com.dfire.grade.manager.exception.ParameterException;
import com.dfire.grade.manager.logger.LoggerFactory;
import com.dfire.grade.manager.vo.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * User:huangtao
 * Date:2016-03-24
 * description：
 */
public class BaseController {

    /**
     * 基于@ExceptionHandler异常处理
     */
    @ExceptionHandler
    public JsonResult exp(Exception ex) {
        LoggerFactory.SYSTEM.error(ex.getMessage(), "系统异常");
        // 根据不同错误转向不同页面
        if (ex instanceof BusinessException) {
            return JsonResult.newInstance2(String.valueOf(Contants.ErrorCode.ERROR_1001), Contants.Message.ERROR_SYSTEM);
        } else if (ex instanceof ParameterException) {
            return JsonResult.newInstance2(String.valueOf(Contants.ErrorCode.ERROR_1002), Contants.Message.ERROR_PARAM);
        } else {
            return JsonResult.newInstance2(String.valueOf(Contants.ErrorCode.ERROR_1003), Contants.Message.ERROR_REQUEST);
        }
    }
}

