package com.yvolabs.zuul.api.gateway.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class ZuulApiGatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulApiGatewayServiceApplication.class, args);
    }

}
