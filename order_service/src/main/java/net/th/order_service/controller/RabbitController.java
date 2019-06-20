package net.th.order_service.controller;


import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class RabbitController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpTemplate amqpTemplate;

    @RequestMapping("/send")
    public String sendRabitMessage(){

        String message = "hello_queue_one"+ new Date();


//      amqpTemplate.convertAndSend(String.valueOf(new Queue("hello_tow")),message);
        for (int i=0; i<100; i++) {
            amqpTemplate.convertAndSend("hello_queue",message+i);
            //rabbitTemplate.
        }

        return null;
    }
}
