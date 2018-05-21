package com.zheng.springboot.service;

import com.zheng.springboot.Application;
import com.zheng.springboot.message.activemq.Producer;
import com.zheng.springboot.mybatis.domain.User;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author Administrator
 * @Date 2018/5/21 10:26
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ActivemqTest {
    @Autowired
    private Producer producer;
    
    @Value("${custom.activemq.queue}")
    private String queueName;
    @Value("${custom.activemq.topic}")
    private String topicName;
    private ActiveMQQueue queue;
    private ActiveMQTopic topic;
    
    @Before
    public void init() {
        queue = new ActiveMQQueue(queueName);
        topic = new ActiveMQTopic(topicName);
    }
    
    @Test
    public void sendQueueTextMessage() {
        producer.sendMessage(queue, "helloworld activemq queue");
    }

    @Test
    public void sendQueueObjectMessage() {
        producer.sendMessage(queue, new User("zhangsan", "123456", 25));
    }
    
    @Test
    public void sendTopicTextMessage() {
        producer.sendMessage(topic, "helloworld activemq topic");
    }

    @Test
    public void sendTopicObjectMessage() {
        producer.sendMessage(topic, new User("王二麻子", "123456", 26));
    }
    
}
