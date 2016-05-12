package com.dfire.grade.manager.controller;

import com.dfire.grade.manager.Contants;
import com.dfire.grade.manager.bean.Classes;
import com.dfire.grade.manager.bean.SignBean;
import com.dfire.grade.manager.service.IClassService;
import com.dfire.grade.manager.service.IStudentClassService;
import com.dfire.grade.manager.utils.RedisUtil;
import com.dfire.grade.manager.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * User:huangtao
 * Date:2016-04-14
 * description：
 */
@RequestMapping("/stu_cla")
@Controller
public class StudentClassController extends BaseController {
    @Autowired
    private IStudentClassService studentClassService;
    @Autowired
    private IClassService classService;
    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public JsonResult joinClass(HttpServletRequest request,
                                @RequestParam(value = "class_id", required = true) String classId) throws Exception {
        SignBean signBean = (SignBean) redisUtil.getValue(Contants.RedisContent.STUDENT_SIGN_CACHE_BY_ID + request.getHeader(Contants.UID), SignBean.class);
        String studentId = signBean.getId();
        JsonResult result = classService.selectClassById(classId);
        if (result.isSuccess() && null != result.getData()) {
            Classes classes = (Classes) result.getData();
            String teacherId = classes.getTeacherId();
            JsonResult rs = studentClassService.createRelationship(studentId, classId, teacherId);
            if (null != rs && rs.isSuccess()) {
                return JsonResult.jsonSuccessMes(Contants.Message.SUCCESS_REQUEST);
            } else {
                return rs;
            }
        } else {
            return JsonResult.failedInstance(Contants.Message.ERROR_NO_CLASS);
        }
    }
}
