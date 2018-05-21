package com.zheng.springboot.resttemplate;

import com.zheng.springboot.mybatis.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 采用rest template在程序中远程调用rest服务提供的接口
 * @Author zhenglian
 * @Date 2018/5/21 22:36
 */
@RestController
@RequestMapping("/rest")
public class RestTemplateController {
    
    @Autowired
    private TemplateService templateService;
    
    
    @GetMapping("/user/{userId}")
    public User getUser(@PathVariable Integer userId) {
        return templateService.getUser(userId);
    }
    
}
