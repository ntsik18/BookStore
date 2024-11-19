package com.nini.bookstore.orderservice.web.controller;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQListener {
    @RabbitListener(queues = "${orders.new-orders-queue}")
    public void handleNewOrder (MyPayLoad payLoad) {
        System.out.println("New Order "+  payLoad.content());
    }
    @RabbitListener(queues = "${orders.delivered-orders-queue}")
    public void handleDeliveredOrder (MyPayLoad payLoad) {
        System.out.println("New Order "+  payLoad.content());
    }

}
