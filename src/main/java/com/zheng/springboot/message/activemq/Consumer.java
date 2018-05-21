package com.zheng.springboot.message.activemq;

import com.zheng.springboot.mybatis.domain.User;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * @Author Administrator
 * @Date 2018/5/21 10:25
 */
@Component
public class Consumer {
    @JmsListener(destination = "m2m_queue", containerFactory = "queueContainerFactory")
    public void customQueueMessage(Message message, Session session) throws JMSException {
        if (message instanceof ActiveMQTextMessage) {
            ActiveMQTextMessage textMessage = (ActiveMQTextMessage) message;
            String text = textMessage.getText();
            System.out.println("received: " + text);
        } else {
            ActiveMQObjectMessage objectMessage = (ActiveMQObjectMessage) message;
            Object object = objectMessage.getObject();
            User user = null;
            if (object instanceof User) {
                user = (User) object;
            }
            System.out.println("received: " + user);
        }
        // 消息手动确认，如果ActivemqConfig在定义template和listenerFactory时设置jmsTemplate.setSessionAcknowledgeMode为自动确认
        // 就不需要添加这行
        message.acknowledge();
    }

    @JmsListener(destination = "m2m_topic", containerFactory = "topicContainerFactory")
    public void customTopicMessage(Message message, Session session) throws JMSException {
        if (message instanceof ActiveMQTextMessage) {
            ActiveMQTextMessage textMessage = (ActiveMQTextMessage) message;
            String text = textMessage.getText();
            System.out.println("received: " + text);
        } else {
            ActiveMQObjectMessage objectMessage = (ActiveMQObjectMessage) message;
            Object object = objectMessage.getObject();
            User user = null;
            if (object instanceof User) {
                user = (User) object;
            }
            System.out.println("received: " + user);
        }
        // 消息手动确认，如果ActivemqConfig在定义template和listenerFactory时设置jmsTemplate.setSessionAcknowledgeMode为自动确认
        // 就不需要添加这行
        message.acknowledge();
    }
}
