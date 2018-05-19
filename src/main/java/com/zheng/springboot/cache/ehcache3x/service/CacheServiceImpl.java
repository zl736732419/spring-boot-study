package com.zheng.springboot.cache.ehcache3x.service;

import org.springframework.stereotype.Service;

import javax.cache.annotation.CacheDefaults;
import javax.cache.annotation.CacheResult;

/**
 * @Author zhenglian
 * @Date 2018/5/19 10:49
 */
@CacheDefaults(cacheName = "cacheService")
@Service
public class CacheServiceImpl implements CacheService {
    @CacheResult
    @Override
    public String cache(String str) {
        return "cached: " + str;
    }
}
