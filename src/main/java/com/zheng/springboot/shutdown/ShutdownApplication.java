package com.zheng.springboot.shutdown;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @Author zhenglian
 * @Date 2018/5/15 9:31
 */
@SpringBootApplication
public class ShutdownApplication {
    /**
     * 通过实现ExitCodeGenerator，返回特定的shutdown状态码
     * @return
     */
    @Bean
    public ExitCodeGenerator exitCodeGenerator() {
        return () -> 42;
    }
    
    public static void main(String[] args) {
        System.exit(SpringApplication.exit(SpringApplication.run(ShutdownApplication.class, args)));
        // 异常也可以实现该接口，用于提供给定的状态码
    }
}
