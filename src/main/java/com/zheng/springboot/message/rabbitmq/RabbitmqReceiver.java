package com.zheng.springboot.message.rabbitmq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author zhenglian
 * @Date 2018/5/20 10:31
 */
@Component
public class RabbitmqReceiver {
    @RabbitListener(queues = {"hello"})
    public void hello(Message message, Channel channel) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        System.out.println("receive: " + new String(message.getBody()));
    }
    
    @RabbitListener(queues = {"topic.message"})
    public void topicMessage(Message message,  Channel channel) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        System.out.println("received: " + new String(message.getBody()));
    }
    
    @RabbitListener(queues = {"fanout.queue1", "fanout.queue2"})
    public void fanoutMessage(Message message,  Channel channel) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        System.out.println("received: " + new String(message.getBody()));
    }
}
