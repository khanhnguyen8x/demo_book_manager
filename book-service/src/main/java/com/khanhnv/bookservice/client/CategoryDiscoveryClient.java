package com.khanhnv.bookservice.client;

import com.khanhnv.bookservice.models.Category;
import com.netflix.appinfo.InstanceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class CategoryDiscoveryClient {

    @Autowired
    DiscoveryClient discoveryClient;

    public Category getCategory(int categoryId) {
        RestTemplate restTemplate = new RestTemplate();
        List<ServiceInstance> instances = discoveryClient.getInstances("categoryservice");

        if (instances == null || instances.size() == 0) return null;

        String serviceUrl = String.format("%s/v1/categories/%s", instances.get(0).getUri(), String.valueOf(categoryId));

        ResponseEntity<Category> restExchange = restTemplate.exchange(serviceUrl, HttpMethod.GET, null, Category.class, categoryId);

        return restExchange.getBody();
    }
}
