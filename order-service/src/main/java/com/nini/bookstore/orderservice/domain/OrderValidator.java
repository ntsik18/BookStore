package com.nini.bookstore.orderservice.domain;


import com.nini.bookstore.orderservice.clients.catalog.Product;
import com.nini.bookstore.orderservice.clients.catalog.ProductServiceClient;
import com.nini.bookstore.orderservice.domain.models.CreateOrderRequest;
import com.nini.bookstore.orderservice.domain.models.OrderItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class OrderValidator {
    private static final Logger log = LoggerFactory.getLogger(OrderValidator.class);
    private final ProductServiceClient client;

    public OrderValidator(ProductServiceClient client) {
        this.client = client;
    }

    void validate (CreateOrderRequest request) {
        Set<OrderItem> items=request.items();
        for (OrderItem item : items) {
            //if code does not exist
            Product product = client.getProdutByCode(item.code())
                    .orElseThrow(()->new InvalidOrderException("Invalid product code "+item.code()));
            //if exists check the price
            if (item.price().compareTo(product.price())!=0) {
                log.error("Product price not matching. Actual price: {}. Received price: {}",
                        product.price(), item.price());
                throw new InvalidOrderException("Product price not matching");
            }
        }

    }
}
