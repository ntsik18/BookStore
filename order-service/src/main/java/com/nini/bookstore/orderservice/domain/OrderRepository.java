package com.nini.bookstore.orderservice.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.parser.Entity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
