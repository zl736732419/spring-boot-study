package com.zheng.springboot.cache.ehcache3x;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * 需要在classpath下明确指定ehcache使用的配置文件
 * @Author zhenglian
 * @Date 2018/5/19 10:28
 */
@Configuration
@EnableCaching
public class Ehcache3ManagerCustomer {
}
