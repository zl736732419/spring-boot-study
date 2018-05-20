package com.zheng.springboot.service;

import com.zheng.springboot.message.rabbitmq.RabbitmqSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author zhenglian
 * @Date 2018/5/20 10:32
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqTest {
    @Autowired
    private RabbitmqSender rabbitmqSender;
    
    @Test
    public void hello() {
        rabbitmqSender.hello();
    }

    @Test
    public void user() {
        rabbitmqSender.user(2);
    }
    
    @Test
    public void topic() {
        rabbitmqSender.topic(3);
    }
    
    @Test
    public void fanoutMessage() {
        rabbitmqSender.fanoutMessage(3);
    }
}
