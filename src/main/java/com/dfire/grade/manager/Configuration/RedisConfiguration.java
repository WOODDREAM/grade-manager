package com.dfire.grade.manager.Configuration;

import redis.clients.jedis.Jedis;

/**
 * User:huangtao
 * Date:2016-03-01
 * description：
 */
public class RedisConfiguration {
    private Jedis jedis;
    private String hostName;
    private int port;

    public void init() {

    }

}
