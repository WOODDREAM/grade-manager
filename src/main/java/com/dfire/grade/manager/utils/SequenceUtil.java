package com.dfire.grade.manager.utils;

import com.dfire.grade.manager.exception.ParameterException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.UUID;

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

    public static boolean isOrBlank(String[][] str) throws ParameterException {
        String message = "参数为空！";
        if (null != str) {
            for (int i = 0; i < str.length; i++) {
                if (!str[i][0].isEmpty()) {
                    return true;
                }
                message = str[i][1];
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
