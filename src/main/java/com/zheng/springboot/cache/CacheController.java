package com.zheng.springboot.cache;

import com.zheng.springboot.cache.ehcache2x.service.MathService;
import com.zheng.springboot.cache.ehcache3x.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhenglian
 * @Date 2018/5/18 16:13
 */
@RestController
@RequestMapping("/cache")
public class CacheController {
    
    @Autowired
    private MathService mathService;
    
    @Autowired
    private CacheService cacheService;
    
    @GetMapping("/pi/{num}")
    public Object pi(@PathVariable Integer num) {
        return mathService.computePiDecimal(num);
    }

    @GetMapping("/{str}")
    public Object pi(@PathVariable String str) {
        return cacheService.cache(str);
    }
}
