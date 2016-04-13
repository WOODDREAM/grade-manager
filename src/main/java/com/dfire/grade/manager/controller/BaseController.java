package com.dfire.grade.manager.controller;

import com.dfire.grade.manager.Contants;
import com.dfire.grade.manager.exception.BusinessException;
import com.dfire.grade.manager.exception.ParameterException;
import com.dfire.grade.manager.exception.SchoolTimeException;
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
//        Map<String, Object> map = new HashMap<>();
//        map.put("message", "系统异常");
//        map.put("cause", ex.getMessage());
        LoggerFactory.REQUEST.error(ex.getMessage(),ex.getCause());
        // 根据不同错误返回不同的错误信息
        if (ex instanceof BusinessException) {
            return JsonResult.newInstance2(String.valueOf(Contants.ErrorCode.ERROR_1001), Contants.Message.ERROR_SYSTEM);
        }
        if (ex instanceof ParameterException) {
            return JsonResult.newInstance2(String.valueOf(Contants.ErrorCode.ERROR_1002), Contants.Message.ERROR_PARAM);
        }
        if (ex instanceof SchoolTimeException) {
            return JsonResult.newInstance2(String.valueOf(Contants.ErrorCode.ERROR_1005), Contants.Message.ERROR_ILLEGAL_SCHOOLTIME);
        }
        return JsonResult.newInstance2(String.valueOf(Contants.ErrorCode.ERROR_1003), Contants.Message.ERROR_REQUEST);
    }
}

