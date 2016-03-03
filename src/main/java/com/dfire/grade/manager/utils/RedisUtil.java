package com.dfire.grade.manager.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * User:huangtao
 * Date:2016-03-01
 * description：
 */
@Component
public class RedisUtil {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    public void getJedis() {
//        stringRedisTemplate.opsForList().set("1",0,"afserg");
        stringRedisTemplate.hasKey("1");
    }

}
