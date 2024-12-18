package com.nini.bookstore.orderservice.clients.catalog;

import com.nini.bookstore.orderservice.ApplicationProperties;
import org.springframework.boot.ResourceBanner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.ClientHttpRequestFactories;
import org.springframework.boot.web.client.ClientHttpRequestFactorySettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import java.time.Duration;

@Configuration
public class CatalogServiceClientConfig {

    @Bean
    public RestClient restClient (ApplicationProperties properties) {
        return RestClient.builder()
                .baseUrl(properties.catalogServiceURL())
                .requestFactory(ClientHttpRequestFactories
                                .get(ClientHttpRequestFactorySettings.DEFAULTS
                                        .withConnectTimeout(Duration.ofSeconds(5))
                                        .withReadTimeout(Duration.ofSeconds(5)))
                        )
                .build();
    }
}
