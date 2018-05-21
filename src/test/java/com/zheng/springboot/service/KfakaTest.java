package com.zheng.springboot.service;

import com.zheng.springboot.Application;
import com.zheng.springboot.message.kafka.KafkaSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author Administrator
 * @Date 2018/5/21 15:33
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class KfakaTest {
    
    @Value("${custom.kafka.topic}")
    private String topicName;
    
    @Autowired
    private KafkaSender kafkaSender;
    
    @Test
    public void test() {
        kafkaSender.send(topicName,  "what how where");
    }
}
