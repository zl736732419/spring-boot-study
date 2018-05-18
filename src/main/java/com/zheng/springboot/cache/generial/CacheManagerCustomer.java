package com.zheng.springboot.cache.generial;

import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author zhenglian
 * @Date 2018/5/18 16:38
 */
@EnableCaching
@Configuration
public class CacheManagerCustomer {

    /**
     * springboot默认的cache实现，通过ConcurrentMap实现
     * @return
     */
    @Bean
    public CacheManagerCustomizer<ConcurrentMapCacheManager> cacheManagerCustomizer() {
        return new CacheManagerCustomizer<ConcurrentMapCacheManager>() {
            @Override
            public void customize(ConcurrentMapCacheManager cacheManager) {
                // 设置不缓存空值
                cacheManager.setAllowNullValues(false);
            }
        };
        
    }
    
}
