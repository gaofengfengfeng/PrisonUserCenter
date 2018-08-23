package com.gaofeng.prisonusercenter.service;

import com.didi.meta.javalib.JLog;
import com.didi.meta.javalib.JToken;
import com.didi.meta.javalib.service.JRedisPoolService;
import com.gaofeng.prisonusercenter.InitConfig;
import redis.clients.jedis.Jedis;

/**
 * @Author: gaofeng
 * @Date: 2018-08-23
 * @Description:
 */
public class BaseService {

    /**
     * 负责token 的相关处理，生成 token并将其存储在redis中，设置过期时间为expire
     *
     * @param key
     * @param expire
     *
     * @return
     */
    public String processToken(String key, Integer expire) {
        String token = JToken.makeToken(key, expire.longValue());
        try {
            Jedis jedis = JRedisPoolService.getJedisPool(InitConfig.REDISPOOL).getResource();
            jedis.setex(key, expire, token);
        } catch (Exception e) {
            JLog.error("redis exception errMsg=" + e.getMessage(), 101222201);
            return null;
        }
        return token;
    }
}
