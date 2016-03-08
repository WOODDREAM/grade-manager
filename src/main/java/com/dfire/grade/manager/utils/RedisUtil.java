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

    public void stringSet() {
//        stringRedisTemplate.opsForList().set("1",0,"afserg");
//        stringRedisTemplate.hasKey("1");
//        stringRedisTemplate.opsForSet().add("1","sdf");
//        stringRedisTemplate.opsForSet().intersect("qw", "qwd");
        stringRedisTemplate.opsForSet().add("ad", "asf", "fdsf");
    }

    /**
     * @param key
     * @param time 有效时间
     * @param o
     */
    public void setValuePre(String key, Object o, int time, TimeUnit unit) throws JsonProcessingException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonStr = objectMapper.writeValueAsString(o);
            redisTemplate.opsForSet().add(key, jsonStr);
            redisTemplate.expire(key, time, unit);
        } catch (JsonProcessingException e) {
            throw e;
        }
    }


    public <T> Object getValue(String key, Class<T> obj) throws IOException {
        T t = null;
        try {
            String value = (String) redisTemplate.opsForValue().get(key);
            ObjectMapper objectMapper = new ObjectMapper();
            t = objectMapper.readValue(value, obj);
        } catch (IOException e) {
            throw e;
        }
        return t;
    }
}
