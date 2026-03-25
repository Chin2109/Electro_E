package com.example.demo.service;

import com.example.demo.dto.request.product.CreateProductRequest;
import com.example.demo.dto.response.product.ProductDetailResponse;
import com.example.demo.entity.Brands;
import com.example.demo.entity.Products;
import com.example.demo.repository.BrandsRepository;
import com.example.demo.repository.ProductsRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {
    private final ProductsRepository productsRepository;
    private final BrandsRepository brandsRepository;

    public ProductService(ProductsRepository productsRepository,
                          BrandsRepository brandsRepository) {
        this.productsRepository = productsRepository;
        this.brandsRepository = brandsRepository;
    }

    @Transactional
    public ProductDetailResponse createProduct(CreateProductRequest request) {
        Brands brand = brandsRepository.getReferenceById(request.getBrandId());

        Products product = new Products();
        product.setName(request.getName());
        product.setSlug(request.getSlug());
        product.setShortDescription(request.getShortDescription());
        product.setDescription(request.getDescription());
        product.setArticle(request.getArticle());
        product.setBrand(brand);
        product.setStatus(1L);
        Products savedProduct = productsRepository.save(product);

        return ProductDetailResponse.builder()
                .productId(savedProduct.getProductId())
                .productName(savedProduct.getName())
                .brandId(brand.getBrandId())
                .brandName(brand.getName())
                .technicalInfo(savedProduct.getDescription())
                .article(savedProduct.getArticle())
                .slug(savedProduct.getSlug())
                .warranties(new ArrayList<>())
                .attributes(new ArrayList<>())
                .variants(new ArrayList<>())
                .build();
    }


}
