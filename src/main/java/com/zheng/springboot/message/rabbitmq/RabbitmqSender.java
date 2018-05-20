package com.zheng.springboot.message.rabbitmq;

import com.zheng.springboot.mybatis.domain.User;
import com.zheng.springboot.mybatis.service.UserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author zhenglian
 * @Date 2018/5/20 10:29
 */
@Component
public class RabbitmqSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private UserService userService;
    
    public void hello() {
        String context = "hello rabbit";
        rabbitTemplate.convertAndSend("hello", context);
    }

    public void user(Integer userId) {
        User user = userService.findById(userId);
        rabbitTemplate.convertAndSend("hello", user);
    }

    public void topic(Integer userId) {
        User user = userService.findById(userId);
        rabbitTemplate.convertAndSend(RabbitmqConfig.TOPIC_EXCHANGE, 
                "topic.helloworld", user);
    }

    public void fanoutMessage(Integer userId) {
        User user = userService.findById(userId);
        rabbitTemplate.convertAndSend(RabbitmqConfig.FANOUT_EXCHANGE,
                "", user);
    }
}
