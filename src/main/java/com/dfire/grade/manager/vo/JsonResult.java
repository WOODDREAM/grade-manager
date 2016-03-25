package com.dfire.grade.manager.vo;

import java.io.Serializable;

/**
 * User:huangtao
 * Date:2016-03-08
 * description：
 */
public class JsonResult implements Serializable {
    private String code;
    private String message;
    private Object data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static JsonResult jsonSuccessData(Object o) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setData(o);
        jsonResult.setCode("1");
        return jsonResult;
    }

    public static JsonResult success() {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode("1");
        return jsonResult;
    }

    public static JsonResult jsonSuccessMes(String message) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode("1");
        jsonResult.setMessage(message);
        return jsonResult;
    }

    public static JsonResult newInstance(String code, String message, Object o) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(code);
        jsonResult.setData(o);
        jsonResult.setMessage(message);
        return jsonResult;
    }

    public static JsonResult newInstance2(String code, String message) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(code);
        jsonResult.setMessage(message);
        return jsonResult;
    }

    public static JsonResult failedInstance(String message) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode("0");
        jsonResult.setMessage(message);
        return jsonResult;
    }
}
