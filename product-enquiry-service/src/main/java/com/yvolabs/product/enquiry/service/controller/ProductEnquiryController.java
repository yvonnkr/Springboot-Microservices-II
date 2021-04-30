package com.yvolabs.product.enquiry.service.controller;

import com.yvolabs.product.enquiry.service.beans.ProductEnquiryBean;
import com.yvolabs.product.enquiry.service.client.ProductStockClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ProductEnquiryController {

    private final ProductStockClient client;

    @Autowired
    public ProductEnquiryController(ProductStockClient client) {
        this.client = client;
    }

    @GetMapping("/product-enquiry/name/{name}/availability/{availability}/unit/{unit}")
    public ProductEnquiryBean getEnquiryOfProduct(@PathVariable String name,
                                                  @PathVariable String availability,
                                                  @PathVariable int unit){

        log.info("Inside getEnquiryOfProduct method of ProductEnquiryController.");
        ProductEnquiryBean productEnquiryBean = client.checkProductStock(name, availability);

        double totalPrice=productEnquiryBean.getProductPrice().doubleValue()*unit;
        double discounts=productEnquiryBean.getDiscountOffer();
        double discountPrice=totalPrice-totalPrice*discounts/100;

        return new ProductEnquiryBean(
                productEnquiryBean.getId(),
                name,
                productEnquiryBean.getProductPrice(),
                availability,
                productEnquiryBean.getDiscountOffer(),
                unit,
                discountPrice,
                productEnquiryBean.getPort()

        );
    }
}
