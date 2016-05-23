package com.dfire.grade.manager.utils;

import com.dfire.grade.manager.exception.ParameterException;
import com.dfire.grade.manager.vo.Schedule;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * User:huangtao
 * Date:2016-03-23
 * description：用于字符串操作
 */
public class SequenceUtil {
    /**
     * 生成32位随机字符串
     *
     * @return
     */
    public static String getSequence() {

        UUID uuid = UUID.randomUUID();
        return uuid.toString().trim().replace("-", "");
    }

    public static List<Schedule> stringToSchedule(String str) throws ParameterException {
        if (null != str) {
            String[] a = str.split(",");
            List<Schedule> schedules = new ArrayList<>();
            for (int i = 0; i < a.length; i++) {
                Schedule schedule = new Schedule();
                int key = 0;
                int value = 0;
                if (i == 0) {
                    key = Integer.parseInt(String.valueOf(a[i].charAt(1)));
                    value = Integer.parseInt(String.valueOf(a[i].charAt(3)));
                } else {
                    String[] b = a[i].split(":");
                    if (b[0].length() > 1) {
                        throw new ParameterException("星期错误");
                    }
                    key = Integer.parseInt(String.valueOf(b[0]));
                    if (i == a.length - 1) {
                        value = Integer.parseInt(String.valueOf(b[1].substring(0, b[1].length() - 1)));
                    } else {
                        value = Integer.parseInt(String.valueOf(b[1]));
                    }
                }
                schedule.setPart(value);
                schedule.setWeekDay(key);
                schedules.add(schedule);
            }
            return schedules;
        }
        return null;
    }

    public static String mapToJson(Map<String, Object> o) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = null;
        try {
            jsonStr = objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonStr;
    }

    public static String Object2Json(Object o) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = null;
        try {
            jsonStr = objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonStr;
    }

    public static boolean isBlank(String str, String message) throws ParameterException {
        if (null != str) {
            if (!str.isEmpty()) {
                if (!str.trim().isEmpty()) {
                    return true;
                }
            }
        }
        throw new ParameterException(message);
    }

    public static boolean isOrBlank(String str,String... str2) throws ParameterException {
        String message = "参数为空！";
        if (null != str) {
            for (int i = 0; i < str2.length; i++) {
                if (!StringUtils.isEmpty(str2[i])) {
                    return true;
                }
                break;
            }
        }
        throw new ParameterException(message);
    }

    public static void main(String[] args) {
        SequenceUtil sequenceUtil = new SequenceUtil();
        System.out.print(sequenceUtil.getSequence());
    }
}
