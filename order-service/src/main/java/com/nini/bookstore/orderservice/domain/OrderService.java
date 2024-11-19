package com.nini.bookstore.orderservice.domain;

import com.nini.bookstore.orderservice.domain.models.CreateOrderRequest;
import com.nini.bookstore.orderservice.domain.models.CreateOrderResponse;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    private final OrderValidator orderValidator;
    /*
    Using both newOrder and savedOrder isn’t strictly necessary unless you need to access or log fields updated by
    the database (like autogenerated IDs or timestamps) right after saving. Since orderRepository.save(newOrder)
    returns the saved entity, you can streamline the code by removing savedOrder and logging directly with newOrder.*/

    public CreateOrderResponse createOrder(String userName, CreateOrderRequest orderRequest) {
        orderValidator.validate(orderRequest);
        OrderEntity newOrder = OrderMapper.convertToEntity(orderRequest);
        newOrder.setUsername(userName);
        OrderEntity savedOrder = orderRepository.save(newOrder);
        log.info("Created order with oderNumber {}", savedOrder.getOrderNumber());
        CreateOrderResponse createOrderResponse = new CreateOrderResponse(savedOrder.getOrderNumber());
        return createOrderResponse;

    }
}