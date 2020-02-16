package com.khanhnv.bookservice.client;

import com.khanhnv.bookservice.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CategoryRestTemplateClient {

    @Autowired
    RestTemplate restTemplate;

    public Category getCategory(int categoryId) {
        ResponseEntity<Category> restExchange =
                restTemplate.exchange(
                        "http://categoryservice/v1/categories/{categoryId}",
                        HttpMethod.GET,
                        null, Category.class, categoryId);

        return restExchange.getBody();
    }
}
