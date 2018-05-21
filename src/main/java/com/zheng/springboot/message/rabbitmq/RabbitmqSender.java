package com.zheng.springboot.message.rabbitmq;

import com.zheng.springboot.mybatis.domain.User;
import com.zheng.springboot.mybatis.service.UserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;

import java.util.UUID;

/**
 * @Author zhenglian
 * @Date 2018/5/20 10:29
 */
//@Component
public class RabbitmqSender {
//    @Autowired
    private RabbitTemplate rabbitTemplate;
//    @Autowired
    private UserService userService;
    
    public void hello() {
        String context = "hello rabbit";
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend("hello", (Object) context, correlationData);
    }

    public void user(Integer userId) {
        User user = userService.findById(userId);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend("hello", user, correlationData);
    }

    public void topic(Integer userId) {
        User user = userService.findById(userId);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(RabbitmqConfig.TOPIC_EXCHANGE, 
                "topic.helloworld", user, correlationData);
    }

    public void fanoutMessage(Integer userId) {
        User user = userService.findById(userId);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(RabbitmqConfig.FANOUT_EXCHANGE,
                "", user, correlationData);
    }
}
