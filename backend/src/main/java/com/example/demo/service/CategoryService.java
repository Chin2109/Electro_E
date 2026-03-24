package com.example.demo.service;

import com.example.demo.dto.request.category.CategorySpecRequest;
import com.example.demo.dto.response.category.CategorySpecResponse;
import com.example.demo.entity.Categories;
import com.example.demo.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    private CategoriesRepository categoriesRepository;

    public CategorySpecResponse createCategorySpec(CategorySpecRequest request) {
        Categories category = new Categories();
        category.setName(request.getName());
        category.setStatus(1L);
    }
}
