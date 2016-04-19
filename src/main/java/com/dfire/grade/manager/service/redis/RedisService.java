package com.dfire.grade.manager.service.redis;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

/**
 * User:huangtao
 * Date:2016-03-01
 * description：
 */
public class RedisService implements ICacheService {

    @Autowired
    private Jedis jedis;

    @Override
    public void returnResource() {
        try {
            jedis.disconnect();
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public Jedis getResource() {
//        jedis.connect();
//        return jedis;
        return null;
    }
}
