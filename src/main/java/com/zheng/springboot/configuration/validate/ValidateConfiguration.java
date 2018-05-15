package com.zheng.springboot.configuration.validate;

import org.springframework.validation.Validator;

/**
 * @Author zhenglian
 * @Date 2018/5/15 18:02
 */
//@Component
public class ValidateConfiguration {
//    @Bean
    public static Validator configurationPropertiesValidator() {
        return new CustomServerValidator();
    }
}
