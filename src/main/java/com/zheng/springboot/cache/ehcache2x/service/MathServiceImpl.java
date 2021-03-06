package com.zheng.springboot.cache.ehcache2x.service;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

/**
 * @Author zhenglian
 * @Date 2018/5/18 16:10
 */
@CacheConfig(cacheNames = "piDecimals")
@Component
public class MathServiceImpl implements MathService {
    @Cacheable
    @Override
    public Integer computePiDecimal(Integer num) {
        if (!Optional.ofNullable(num).isPresent() || Objects.equals(num, 0)) {
            return null;
        }
        int result = num / 360;
        return result;
    }
}
