package com.dfire.grade.manager.service.redis;

import redis.clients.jedis.Jedis;

/**
 * User:huangtao
 * Date:2016-03-01
 * description：
 */
public interface ICacheService {
    /**
     * 收回连接池
     *
     */
    public void returnResource();

    /**
     * 取得Jedis连接
     *
     * @return
     */
    public Jedis getResource();

}
