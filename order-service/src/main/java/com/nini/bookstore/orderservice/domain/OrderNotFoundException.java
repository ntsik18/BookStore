package com.nini.bookstore.orderservice.domain;


public class OrderNotFoundException extends RuntimeException{

    public OrderNotFoundException(String message) {
        super(message);
    }

    public static OrderNotFoundException forCode (String orderNumber) {
        return new OrderNotFoundException("Order with Number " + orderNumber + " not found");

    }
}
