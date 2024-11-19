package com.nini.bookstore.orderservice.web.controller;


import com.nini.bookstore.orderservice.ApplicationProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitmqController {
    private final RabbitTemplate rabbitTemplate;
    private final ApplicationProperties applicationProperties;

    RabbitmqController(RabbitTemplate rabbitTemplate, ApplicationProperties applicationProperties) {
        this.rabbitTemplate=rabbitTemplate;
        this.applicationProperties = applicationProperties;
    }

    @PostMapping("/send")
    private void sendMessage (@RequestBody MyMessage myMessage) {
        rabbitTemplate.convertAndSend(
                applicationProperties.ordersEventsExchange(),
                myMessage.routingKey(),
                myMessage.myPayLoad()
        );
    }
}

record MyMessage(String routingKey, MyPayLoad myPayLoad) {}
record MyPayLoad(String content) {}
