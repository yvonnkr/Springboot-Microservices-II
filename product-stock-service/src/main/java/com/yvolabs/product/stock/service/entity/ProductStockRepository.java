package com.yvolabs.product.stock.service.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductStockRepository extends JpaRepository<ProductStock, Long> {
    ProductStock findByProductNameAndProductAvailability(String productName, String productAvailability);
}
