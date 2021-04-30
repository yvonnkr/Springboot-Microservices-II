package com.yvolabs.product.enquiry.service.client;

import com.yvolabs.product.enquiry.service.beans.ProductEnquiryBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="product-stock-service", url="localhost:8800")
public interface ProductStockClient {

    @GetMapping("/check-product-stock/productName/{productName}/productAvailability/{productAvailability}")
    public ProductEnquiryBean checkProductStock(@PathVariable String productName,
                                                @PathVariable String productAvailability);
}
