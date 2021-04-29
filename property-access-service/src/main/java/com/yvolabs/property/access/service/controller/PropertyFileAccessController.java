package com.yvolabs.property.access.service.controller;

import com.yvolabs.property.access.service.beans.PropertyAccessBean;
import com.yvolabs.property.access.service.beans.PropertyAccessValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



@RestController
@RequestMapping("/access")
public class PropertyFileAccessController {

    private final PropertyAccessBean propertyAccessBean;

    @Autowired
    public PropertyFileAccessController(PropertyAccessBean propertyAccessBean) {
        this.propertyAccessBean = propertyAccessBean;
    }

    @GetMapping("/accessPropertyFile")
    public PropertyAccessValue accessPropertyFile() {

        refreshActuator();
        return new PropertyAccessValue(propertyAccessBean.getName(),
                propertyAccessBean.getDescription());
    }

    private void refreshActuator(){
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:8100/actuator/refresh";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<?> entity = new HttpEntity<>(headers);
        restTemplate.postForEntity(baseUrl,entity,String.class);
        // ResponseEntity<String> results = restTemplate.postForEntity(baseUrl,entity,String.class);

    }
}
