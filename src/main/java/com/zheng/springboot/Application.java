package com.zheng.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * spring boot入口
 * @SpringBootApplication 相当于 @EnableAutoConfiguration @ComponentScan @Configuration
 * @EnableAutoConfiguration springboot根据依赖的jar自动配置项目
 * @ComponentScan 这里相当于对com.zheng.springboot包及其子包进行扫描
 * @Configuration 允许注册或者导入其他bean,这些bean以(@Controller, @Component, @Service, @Repository)标记
 * @Author zhenglian
 * @Date 2018/5/14 11:19
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        // 彻底禁用devtools重新加载功能
//        System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(Application.class, args);
    }
}
