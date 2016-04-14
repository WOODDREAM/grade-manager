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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * User:huangtao
 * Date:2016-04-14
 * description：
 */
@RequestMapping("/stu_cla")
@RestController
public class StudentClassController extends BaseController {
    @Autowired
    private IStudentClassService studentClassService;
    @Autowired
    private IClassService classService;
    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public JsonResult joinClass(HttpServletRequest request,
                                @RequestParam(value = "class_id", required = true) String classId) throws Exception {
        SignBean signBean = (SignBean) redisUtil.getValue(Contants.RedisContent.STUDENT_CACHE_BY_ID + request.getHeader(Contants.UID), SignBean.class);
        String studentId = signBean.getId();
        Classes classes = classService.selectClassById(classId);
        String teacherId = classes.getTeacherId();
        JsonResult result = studentClassService.createRelationship(studentId, teacherId, classId);
        if (null != result && result.isSuccess()) {
            return JsonResult.jsonSuccessMes(Contants.Message.SUCCESS_REQUEST);
        } else {
            return JsonResult.failedInstance(Contants.Message.ERROR_REQUEST);
        }
    }
}
