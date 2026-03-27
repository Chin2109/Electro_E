package com.example.demo.service;

import com.example.demo.dto.request.product.AddAttributeRequest;
import com.example.demo.dto.request.product.CreateProductRequest;
import com.example.demo.dto.response.product.ProductDetailResponse;
import com.example.demo.entity.Brands;
import com.example.demo.entity.Products;
import com.example.demo.entity.VariantAttribute;
import com.example.demo.repository.BrandsRepository;
import com.example.demo.repository.ProductsRepository;
import com.example.demo.repository.VariantAttributeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private final ProductsRepository productsRepository;
    private final BrandsRepository brandsRepository;
    private final VariantAttributeRepository variantAttributeRepository;

    public ProductService(ProductsRepository productsRepository,
                          BrandsRepository brandsRepository,
                          VariantAttributeRepository variantAttributeRepository) {
        this.productsRepository = productsRepository;
        this.brandsRepository = brandsRepository;
        this.variantAttributeRepository = variantAttributeRepository;
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

    @Transactional
    public VariantAttribute addAttributetoProduct(AddAttributeRequest request) {
        Products product = productsRepository.getReferenceById(request.getProductId());

        VariantAttribute variantAttribute = new VariantAttribute();
        variantAttribute.setProduct(product);
        variantAttribute.setName(request.getName());
        variantAttribute.setCode(request.getCode());
        return variantAttributeRepository.save(variantAttribute);
    }

    @Transactional
    public List<VariantAttribute> getAttributes(Long productId) {
        Products products = productsRepository.getReferenceById(productId);

        return products.getVariantAttributeList();
    }

}
