package com.zheng.springboot.service;

import com.zheng.springboot.message.kafka.KafkaSender;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * @Author Administrator
 * @Date 2018/5/21 15:33
 */
public class KfakaTest extends BaseServiceTest {
    
    @Value("${custom.kafka.topic}")
    private String topicName;
    
    @Autowired
    private KafkaSender kafkaSender;
    
    @Test
    public void test() {
        kafkaSender.send(topicName,  "what how where");
    }
    
    @Test
    public void testKV() {
        kafkaSender.send(topicName, "hello", "what the fuck!");
    }
    
}
