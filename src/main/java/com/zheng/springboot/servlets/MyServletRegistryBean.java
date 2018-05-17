package com.zheng.springboot.servlets;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * @Author zhenglian
 * @Date 2018/5/17 13:47
 */
@Configuration
public class MyServletRegistryBean {
    
    @Bean
    public ServletRegistrationBean myServlet01() {
        ServletRegistrationBean bean = new ServletRegistrationBean();
        bean.setServlet(new MyServlet01());
        bean.addUrlMappings("/servlet01");
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE + 101);
        return bean;
    }

    @Bean
    public ServletRegistrationBean myServlet02() {
        ServletRegistrationBean bean = new ServletRegistrationBean();
        bean.setServlet(new MyServlet02());
        bean.addUrlMappings("/servlet02");
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE + 100);
        return bean;
    }
}
