package com.zheng.springboot.validate;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Pattern;

/**
 * @Author zhenglian
 * @Date 2018/5/21 23:11
 */
@Validated
public interface ValidateService {
    String hello(@Pattern(regexp = "^\\d{1}[abc]$") String str);
}
