package com.nini.bookstore.catalog.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
// it is slice test so will be faster

@DataJpaTest(
        properties = {
            "spring.test.database.replace=none", // this DataJpaTest by default uses
            // inMemory db, and you want to disable that and use docker
            "spring.datasource.url=jdbc:tc:postgresql:16-alpine:///db"
        })
// @Import(TestcontainersConfiguration.class)  -this will spin up all configured images (rabbitMQ)
@Sql("/test-data.sql")
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    void shouldGetAllProducts() {
        List<ProductEntity> products = productRepository.findAll();
        assertThat(products).hasSize(15);
    }

    @Test
    void shouldGetProductByCode() {
        ProductEntity productEntity = productRepository.findByCode("P100").orElseThrow();
        assertThat(productEntity.getCode()).isEqualTo("P100");
        assertThat(productEntity.getPrice()).isEqualTo(new BigDecimal("34.0"));
    }

    @Test
    void shouldReturnEmpty() {

        assertThat(productRepository.findByCode("a")).isEmpty();
    }
}
