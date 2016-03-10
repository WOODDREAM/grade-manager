package com.dfire.grade.manager.Configuration;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;


/**
 * User:huangtao
 * Date:2016-03-01
 * description：
 */
public class RedisConfiguration {
    @Autowired
    private Jedis jedis;

    public void setNx(String key, Object o) {
        if (null != key && null != o) {
            String json = JSONObject.toJSONString(o);
            jedis.setnx(key, json);
        }
    }
}
