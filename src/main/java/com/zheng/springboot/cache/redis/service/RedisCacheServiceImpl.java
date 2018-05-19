package com.zheng.springboot.cache.redis.service;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @Author zhenglian
 * @Date 2018/5/19 14:36
 */
@Service
@CacheConfig(cacheNames = "redis-cache")
public class RedisCacheServiceImpl implements RedisCacheService {
    @Cacheable
    @Override
    public String hello(String msg) {
        return "redis cache: " + msg;
    }
}
