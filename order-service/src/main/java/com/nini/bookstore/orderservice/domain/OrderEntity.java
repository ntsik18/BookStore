package com.nini.bookstore.orderservice.domain;


import com.nini.bookstore.orderservice.domain.models.Address;
import com.nini.bookstore.orderservice.domain.models.Customer;
import com.nini.bookstore.orderservice.domain.models.OrderStatus;
import com.rabbitmq.client.DnsSrvRecordAddressResolver;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "orders")
@Builder
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="order_id_generator")
    @SequenceGenerator(sequenceName ="order_id_seq", name = "order_id_generator")
    private Long id;
    @Column(unique = true, name ="order_number", nullable = false )
    private String orderNumber;
    @Column(unique = true, nullable = false)
    private String username;
    @Embedded
    @AttributeOverrides
            (value = {
                    @AttributeOverride(name = "name", column = @Column(name = "customer_name")),
                    @AttributeOverride(name="email", column= @Column(name = "customer_email")),
                    @AttributeOverride(name="phone", column = @Column(name="customer_phone"))
            })
    private Customer customer;

    @Embedded
    @AttributeOverrides(
            value = {
                    @AttributeOverride(name = "line1", column = @Column(name = "delivery_address_line1")),
                    @AttributeOverride(name = "line2", column = @Column(name = "delivery_address_line2")),
                    @AttributeOverride(name = "city", column = @Column(name = "delivery_address_city")),
                    @AttributeOverride(name = "state", column = @Column(name = "delivery_address_state")),
                    @AttributeOverride(name = "zip", column = @Column(name = "delivery_address_zip_code")),
                    @AttributeOverride(name = "country", column = @Column(name = "delivery_address_country"))
            }
    )
    private Address deliveryAddress;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private String comments;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt=LocalDateTime.now();
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "order")
    private Set<OrderItemsEntity> orderItemsEntities;

}
