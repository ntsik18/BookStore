package com.nini.bookstore.orderservice.domain;

import com.nini.bookstore.orderservice.domain.models.CreateOrderRequest;
import com.nini.bookstore.orderservice.domain.models.OrderItem;
import com.nini.bookstore.orderservice.domain.models.OrderStatus;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

class OrderMapper {

     static OrderEntity convertToEntity(CreateOrderRequest request) {
         OrderEntity newOrder = new OrderEntity();
         newOrder.setOrderNumber(UUID.randomUUID().toString());
         newOrder.setStatus(OrderStatus.NEW);
         newOrder.setCustomer(request.customer());
         newOrder.setDeliveryAddress(request.deliveryAddress());
         Set<OrderItemsEntity> orderItems = new HashSet<>();
         for (OrderItem item : request.items()) {
             OrderItemsEntity orderItem=new OrderItemsEntity();
             orderItem.setCode(item.code());
             orderItem.setName(item.name());
             orderItem.setPrice(item.price());
             orderItem.setQuantity(item.quantity());
             orderItem.setOrder(newOrder);
             orderItems.add(orderItem);
         }
         newOrder.setOrderItemsEntities(orderItems);
         return  newOrder;




     }
}
