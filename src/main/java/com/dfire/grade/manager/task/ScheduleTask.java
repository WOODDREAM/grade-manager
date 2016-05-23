package com.dfire.grade.manager.task;

import com.dfire.grade.manager.bean.ClassStart;
import com.dfire.grade.manager.mapper.ClassStartMapper;
import com.dfire.grade.manager.service.IClassService;
import com.dfire.grade.manager.service.IClassStartService;
import com.dfire.grade.manager.utils.RedisUtil;
import com.dfire.grade.manager.vo.JsonResult;
import com.mysql.jdbc.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Yin on 2016/5/23.
 */
@Component
public class ScheduleTask extends TimerTask {
    //定时：一天
    long daySpan = 1 * 60 * 1000;

    //每天零晨执行任务
    final SimpleDateFormat dataForm = new SimpleDateFormat("yyyy-mm-dd '23:10:00'");
    private Date startTime;
    @Autowired
    private IClassStartService classStartService;
    @Autowired
    private ClassStartMapper classStartMapper;

    private Timer timer = new Timer();

    public void executeTask() throws ParseException {
        startTime = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").parse(dataForm.format(new Date()));
        // 以每24小时执行一次
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    System.out.println("scheduleTask start :++++++++++++++++");
                    Date date = new Date();
                    JsonResult result = classStartService.selectByTime(date);
                    if (result.isSuccess() && null != result.getData()) {
                        List<ClassStart> classStarts = (List<ClassStart>) result.getData();
                        for (ClassStart classStart : classStarts) {
                            long from = classStart.getCreateTime().getTime();
                            long to = date.getTime();
                            int count = (int) ((to - from) / (1000));
//                                int count = (int) ((to - from) / (1000 * 3600 * 24 * 7 * 2));
                            if (count >= 1) {
                                classStartService.endClass(classStart.getClassId(), classStart.getClassNo());
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, startTime, daySpan);

    }

    @Override
    public void run() {
        System.out.println("scheduleTask start :++++++++++++++++");
        Date date = new Date();
        JsonResult result = null;
        try {
            result = classStartService.selectByTime(date);
            if (result.isSuccess() && null != result.getData()) {
                List<ClassStart> classStarts = (List<ClassStart>) result.getData();
                for (ClassStart classStart : classStarts) {
                    long from = classStart.getCreateTime().getTime();
                    long to = date.getTime();
//                    int count = (int) ((to - from) / (1000));
                    int count = (int) ((to - from) / (1000 * 3600 * 24 * 7 * 2));
                    if (count >= 1) {
                        classStartService.endClass(classStart.getClassId(), classStart.getClassNo());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
