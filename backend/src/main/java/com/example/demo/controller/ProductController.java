package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.request.product.CreateProductRequest;
import com.example.demo.dto.response.product.ProductDetailResponse;
import com.example.demo.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //create product
    @PostMapping("/create")
    ApiResponse<ProductDetailResponse> createProduct(@RequestBody CreateProductRequest request) {
        return ApiResponse.<ProductDetailResponse>builder()
                .message("New Product created successfully")
                .data(productService.createProduct(request))
                .build();
    }

    //add to product: category/spec; variant
}
