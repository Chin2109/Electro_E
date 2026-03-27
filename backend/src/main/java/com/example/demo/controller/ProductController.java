package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.request.product.AddAttributeRequest;
import com.example.demo.dto.request.product.CreateProductRequest;
import com.example.demo.dto.response.product.ProductDetailResponse;
import com.example.demo.entity.VariantAttribute;
import com.example.demo.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    //update product

    //delete product (for testing)

    //add to product: category/spec; variant;

    //add attribute to product
    @PostMapping("/add-attribute")
    ApiResponse<VariantAttribute> addAttributeToProduct(@RequestBody AddAttributeRequest request) {
        return ApiResponse.<VariantAttribute>builder()
                .message("New attribute added successfully")
                .data(productService.addAttributetoProduct(request))
                .build();
    }

    //show all attribute of a product (to choose in product variant)
    @GetMapping("/attribute/{productId}")
    ApiResponse<List<VariantAttribute>> getAttributes(@PathVariable("productId") Long productId) {
        return ApiResponse.<List<VariantAttribute>>builder()
                .message("Attribute list")
                .data(productService.getAttributes(productId))
                .build();
    }

    //add product variant


}
