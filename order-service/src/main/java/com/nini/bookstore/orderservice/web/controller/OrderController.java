package com.nini.bookstore.orderservice.web.controller;

import com.nini.bookstore.orderservice.domain.OrderService;
import com.nini.bookstore.orderservice.domain.SecurityService;
import com.nini.bookstore.orderservice.domain.models.CreateOrderRequest;
import com.nini.bookstore.orderservice.domain.models.CreateOrderResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final SecurityService securityService;
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    private final OrderService orderService;

    public OrderController(SecurityService securityService, OrderService orderService) {
        this.securityService = securityService;
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CreateOrderResponse createOrderResponse (@Valid @RequestBody CreateOrderRequest orderRequest) {

        String userName = securityService.getLoginUserName();
        log.info("Create order for user: {}", userName);
       CreateOrderResponse createOrderResponse =   orderService.createOrder(userName, orderRequest);
       return createOrderResponse;


    }



}
