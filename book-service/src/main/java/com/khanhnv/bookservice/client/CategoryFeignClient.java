package com.khanhnv.bookservice.client;

import com.khanhnv.bookservice.models.Category;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("categoryservice")
public interface CategoryFeignClient {

    @RequestMapping(method = RequestMethod.GET,
            value = "/v1/categories/{categoryId}",
            consumes = "application/json")
    Category getCategory(@PathVariable("categoryId") int categoryId);

}
