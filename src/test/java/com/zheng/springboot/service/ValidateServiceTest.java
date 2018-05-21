package com.zheng.springboot.service;

import com.zheng.springboot.validate.ValidateService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author zhenglian
 * @Date 2018/5/21 23:23
 */
public class ValidateServiceTest extends BaseServiceTest {
    @Autowired
    private ValidateService validateService;
    
    @Test
    public void test() {
        String response = validateService.hello("1aa");
        System.out.println(response);
    }
}
