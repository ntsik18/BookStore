package com.nini.bookstore.orderservice.clients.catalog;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Optional;

@Service
public class ProductServiceClient {
    private static final Logger log = LoggerFactory.getLogger(ProductServiceClient.class);
    private final RestClient restClient;

    public ProductServiceClient(RestClient restClient) {
        this.restClient = restClient;
    }

    //Timeout is too complicated for resilence4J, so use it on RestClient
    @CircuitBreaker(name = "catalog-service")
    @Retry(name = "catalog-service", fallbackMethod = "getProductByCodeFallback")  //retry perspective you should not be handlint  it, exception should be thrown
    public Optional<Product> getProdutByCode(String code) {
        log.info("Fetching product for code {}", code);
//        try {
            var product = restClient
                    .get()
                    .uri("/products/{code}", code)
                    .retrieve()
                    .body(Product.class);
            return Optional.ofNullable(product);
//        }catch (Exception e) {
//            log.error("Error fetching product for code: {}", code, e);
//            return Optional.empty();
//        }
    }

    //fallback -  what retry should do if it fails, this logic comes from business preferences

    Optional<Product> getProductByCodeFallback(String code, Throwable t) {
        System.out.println("getProductByCodeFallback for code " + code);
        return Optional.empty();
    }
}
