package com.zheng.springboot.validate;

import org.springframework.stereotype.Service;


/**
 * @Author zhenglian
 * @Date 2018/5/21 23:11
 */
@Service
public class ValidateServiceImpl implements ValidateService {
    @Override
    public String hello(String str) {
        return "yes: " + str;
    }
}
