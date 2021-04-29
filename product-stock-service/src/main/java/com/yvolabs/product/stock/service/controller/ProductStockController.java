package com.yvolabs.product.stock.service.controller;

import com.yvolabs.product.stock.service.beans.ProductStockBean;
import com.yvolabs.product.stock.service.entity.ProductStock;
import com.yvolabs.product.stock.service.entity.ProductStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class ProductStockController {
    private final Environment environment;
    private final ProductStockRepository repository;

    @Autowired
    public ProductStockController(Environment environment, ProductStockRepository productStockRepository) {
        this.environment = environment;
        this.repository = productStockRepository;
    }

    @GetMapping("/check-product-stock/productName/{productName}/productAvailability/{productAvailability}")
    public ProductStockBean checkProductStock(@PathVariable String productName,
                                              @PathVariable String productAvailability){
        ProductStock productStock = repository.findByProductNameAndProductAvailability(productName,productAvailability);

        return new ProductStockBean(
                productStock.getId(),
                productStock.getProductName(),
                productStock.getProductPrice(),
                productStock.getProductAvailability(),
                productStock.getDiscountOffer(),
                Integer.parseInt(Objects.requireNonNull(environment.getProperty("local.server.port")))
        );
    }
}
