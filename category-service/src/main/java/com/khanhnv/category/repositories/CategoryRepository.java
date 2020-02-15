package com.khanhnv.category.repositories;

import com.khanhnv.category.models.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {

    Category getByCategoryId(int categoryId);

}
