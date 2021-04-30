package com.yvolabs.spring.cloud.api.gateway.service.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  Can configure routes here alternative to using application.yml
 */
@Configuration
public class CloudRoutingConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(
                        r -> r.path("/product-enquiry/**")
                                .uri("http://localhost:8700/")
                                .id("product-enquiry-service")
                )
                .route(
                        r -> r.path("/check-product-stock/**")
                                .uri("http://localhost:8800/")
                                .id("product-stock-service")
                )
                .build();
    }
}
