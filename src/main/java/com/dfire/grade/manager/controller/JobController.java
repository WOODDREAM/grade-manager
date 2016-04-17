package com.dfire.grade.manager.controller;

import com.dfire.grade.manager.Contants;
import com.dfire.grade.manager.bean.SignBean;
import com.dfire.grade.manager.service.IJobService;
import com.dfire.grade.manager.utils.RedisUtil;
import com.dfire.grade.manager.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * User:huangtao
 * Date:2016-04-13
 * description：
 */
@RestController
@RequestMapping("/job")
public class JobController extends BaseController {
    @Autowired
    private IJobService jobService;
    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public JsonResult createJob(HttpServletRequest request,
                                @RequestParam(value = "job_id", required = false, defaultValue = "") String jobId,
                                @RequestParam(value = "name", required = true) String name,
                                @RequestParam(value = "class_id", required = true) String classId,
                                @RequestParam(value = "is_need_answer", required = true) Boolean isAnswer,
                                @RequestParam(value = "type", required = true) Integer type,
                                @RequestParam(value = "detail", required = false, defaultValue = "") String detail) throws Exception {
        SignBean signBean = (SignBean) redisUtil.getValue(Contants.RedisContent.TEACHER_SIGN_CACHE_BY_ID + request.getHeader(Contants.UID), SignBean.class);
        String teacherId = signBean.getId();
        JsonResult jobResult;
        jobResult = jobService.createJob(teacherId, classId, name, detail, type, isAnswer, jobId);
        return jobResult;
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public JsonResult findAnswer(HttpServletRequest request,
                                 @RequestParam(value = "teacher_id", required = false) String teacherId,
                                 @RequestParam(value = "class_id", required = false) String classId,
                                 @RequestParam(value = "job_id", required = false) String jobId) throws Exception {
        SignBean signBean = (SignBean) redisUtil.getValue(Contants.RedisContent.STUDENT_SIGN_CACHE_BY_ID + request.getHeader(Contants.UID), SignBean.class);
        String studentId = signBean.getId();
        Map<String, Object> map = new HashMap<>();
        map.put("studentId", studentId);
        if (null != teacherId && !teacherId.isEmpty()) {
            map.put("teacherId", teacherId);
        }
        if (null != jobId && !jobId.isEmpty()) {
            map.put("jobId", jobId);
        }
        if (null != classId && !classId.isEmpty()) {
            map.put("classId", classId);
        }
        JsonResult rs = jobService.selectJob(map);
        if (rs.isSuccess()) {
            if (null != rs.getData() && !CollectionUtils.isEmpty((Collection<?>) rs.getData())) {
                return JsonResult.jsonSuccessData(rs);
            }
            return JsonResult.failedInstance(Contants.Message.NOT_FIND_JOB);
        } else {
            return JsonResult.failedInstance(Contants.Message.ERROR_REQUEST);
        }
    }
}
