package com.zheng.springboot.controller;

import com.zheng.springboot.service.EchoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhenglian
 * @Date 2018/5/14 11:28
 */
@RestController(value = "/echo")
public class EchoController {
    @Autowired
    private EchoService echoService;

    @Value("${spring.devtools.restart.enabled}")
    private boolean devtoolsRestartEnabled;
    
    
    @RequestMapping("/echo/{msg}")
    public String echo(@PathVariable String msg) {
        String echo = echoService.echo(msg);
        System.out.println(devtoolsRestartEnabled);
        return echo;
    }
}
