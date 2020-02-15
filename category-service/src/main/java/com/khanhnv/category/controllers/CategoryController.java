package com.khanhnv.category.controllers;

import com.khanhnv.category.models.Category;
import com.khanhnv.category.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "v1/categories/")
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping(value = "/{categoryId}")
    public Category getCategory(@PathVariable("categoryId") int categoryId) {
        return categoryRepository.getByCategoryId(categoryId);
    }

    @GetMapping()
    public List<Category> getAllCategory() {
        Iterable<Category> iterable = categoryRepository.findAll();
        List<Category> categories = new ArrayList<>();
        iterable.forEach(category -> categories.add(category));
        return categories;
    }

    @PostMapping()
    public Category createCategory(@RequestBody(required = true) Category category) {
        return categoryRepository.save(category);
    }

    @PutMapping(value = "/{categoryId}")
    public Category editCategory(@RequestBody(required = true) Category category) {
        return categoryRepository.save(category);
    }

    @DeleteMapping(value = "/{categoryId}")
    public void deleteCategory(@RequestBody(required = true) Category category) {
        categoryRepository.delete(category);
    }
}
