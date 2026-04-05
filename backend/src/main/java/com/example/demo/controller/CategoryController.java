package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.request.category.CategorySpecRequest;
import com.example.demo.dto.response.category.CategorySpecResponse;
import com.example.demo.entity.Categories;
import com.example.demo.service.CategoryService;
import org.springframework.web.bind.annotation.*;

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

    // API Sửa Category và quản lý Specs bên trong: thêm spec vào cate
    @PutMapping("/{id}")
    public ApiResponse<Categories> updateCategory(@PathVariable Long id, @RequestBody Categories request) {
        return ApiResponse.<Categories>builder()
                .message("Update Category and Attributes successfully")
                .data(categoryService.updateCategoryAndSpecs(id, request))
                .build();
    }

    // API Xóa Category (Tự động xóa sạch Specs đi kèm)
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ApiResponse.<String>builder()
                .message("Delete Category successfully")
                .data("Deleted ID: " + id)
                .build();
    }


    //get all category; spec attribute; values

    //get category by id


}
