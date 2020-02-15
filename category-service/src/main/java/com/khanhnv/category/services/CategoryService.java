package com.khanhnv.category.services;

import com.khanhnv.category.models.Category;
import com.khanhnv.category.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Category getCategory(int categoryId) {
        return categoryRepository.getByCategoryId(categoryId);
    }

    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }
}
