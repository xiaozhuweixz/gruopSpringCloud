package net.th.order_service.service.impl;


import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


/**
 * 消费者用来监听消息queues = "hello_queue"
 */
@Component
public class RabbitServiceImpl {


    @RabbitHandler       //增加Handler方法
    @RabbitListener(queues = "hello_queue")   //监听的队列名称
    public void process(String message){
        System.out.printf("message:" + message);
        System.out.printf("---------------------");

    }
}
