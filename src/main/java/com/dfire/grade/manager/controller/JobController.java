package com.dfire.grade.manager.controller;

import com.dfire.grade.manager.Contants;
import com.dfire.grade.manager.vo.JsonResult;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * User:huangtao
 * Date:2016-04-13
 * description：
 */
@RestController
public class JobController extends BaseController {
    public JsonResult createJob(HttpServletRequest request,
                                @RequestParam(value = "name", required = true) String name,
                                @RequestParam(value = "class_id", required = true) String classId,
                                @RequestParam(value = "is_need_answer", required = true) Boolean isAnswer,
                                @RequestParam(value = "type", required = true) Integer type,
                                @RequestParam(value = "detail", required = false, defaultValue = "") String detai) {


        return JsonResult.jsonSuccessMes(Contants.Message.SUCCESS_REQUEST);
    }

}
