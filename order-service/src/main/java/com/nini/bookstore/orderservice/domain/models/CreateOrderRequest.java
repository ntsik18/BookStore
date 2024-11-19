package com.nini.bookstore.orderservice.domain.models;

import com.nini.bookstore.orderservice.domain.OrderItemsEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.Set;

public record CreateOrderRequest(
        @Valid @NotEmpty(message = "Items can not be empty") Set <OrderItem> items,
        @Valid Customer customer,
        @Valid Address deliveryAddress
) {
}
