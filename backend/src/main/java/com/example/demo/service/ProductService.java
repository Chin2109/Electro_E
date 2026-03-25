package com.example.demo.service;

import com.example.demo.dto.request.product.CreateProductRequest;
import com.example.demo.dto.response.product.ProductDetailResponse;
import com.example.demo.entity.Products;
import com.example.demo.repository.ProductsRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductsRepository productsRepository;

    public ProductService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public ProductDetailResponse createProduct(CreateProductRequest request) {
        Products product = new Products();
        product.setName(request.getName());
    }


}
