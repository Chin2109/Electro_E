package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.request.category.CategorySpecRequest;
import com.example.demo.dto.response.category.CategorySpecResponse;
import com.example.demo.service.CategoryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    //create category and spec attribute
    //reject duplicate name
    @PostMapping("/create")
    ApiResponse<CategorySpecResponse> createCategory(@RequestBody CategorySpecRequest request) {
        return ApiResponse.<CategorySpecResponse>builder()
                .message("Create Category and Spec successfully")
                .data(categoryService.createCategorySpec(request))
                .build();
    }

    //add attributes to a category

    //get all category and spec attribute

    //get category by id

    //update

    //delete
}
