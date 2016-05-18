package com.dfire.grade.manager.controller;

import com.dfire.grade.manager.logger.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
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
    @Async
    @ExceptionHandler
    public void exp(Exception ex) throws Exception {
//        Map<String, Object> map = new HashMap<>();
//        map.put("message", "系统异常");
//        map.put("cause", ex.getMessage());
        LoggerFactory.REQUEST.error(ex.getMessage(), ex.getCause());
        throw new Exception();
        // 根据不同错误返回不同的错误信息
//        if (ex instanceof BusinessException) {
//            return JsonResult.newInstance2(String.valueOf(Contants.ErrorCode.ERROR_1001), Contants.Message.ERROR_SYSTEM);
//        }
//        if (ex instanceof ParameterException) {
//            return JsonResult.newInstance2(String.valueOf(Contants.ErrorCode.ERROR_1002), ex.getMessage());
//        }
//        if (ex instanceof SchoolTimeException) {
//            return JsonResult.newInstance2(String.valueOf(Contants.ErrorCode.ERROR_1005), Contants.Message.ERROR_ILLEGAL_SCHOOLTIME);
//        }
//        return JsonResult.newInstance2(String.valueOf(Contants.ErrorCode.ERROR_1003), Contants.Message.ERROR_SYSTEM);
    }
}

