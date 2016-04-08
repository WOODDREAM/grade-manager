package com.dfire.grade.manager.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * User:huangtao
 * Date:2016-03-01
 * description：
 */
@Component
public class RedisUtil {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * @param key
     * @param time 有效时间
     * @param o
     */
    public void setValuePre(String key, Object o, int time, TimeUnit unit) throws JsonProcessingException {
        try {
//            byte[] bytes = key.getBytes("ISO8859-1");
//            String str = new String(bytes,"utf-8");
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonStr = objectMapper.writeValueAsString(o);
            redisTemplate.opsForValue().set(key, jsonStr);
            redisTemplate.expire(key, time, unit);
        } catch (JsonProcessingException e) {
            throw e;
        }
    }


    public <T> Object getValue(String key, Class<T> obj) throws IOException {
        T t = null;
        try {
            String value = (String) redisTemplate.opsForValue().get(key);
            if (null == value) {
                return null;
            }
            ObjectMapper objectMapper = new ObjectMapper();
            t = objectMapper.readValue(value, obj);
        } catch (IOException e) {
            throw e;
        }
        return t;
    }
}
