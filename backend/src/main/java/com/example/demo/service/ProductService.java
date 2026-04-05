package com.example.demo.service;

import com.example.demo.dto.request.product.AddAttributeRequest;
import com.example.demo.dto.request.product.CreateProductRequest;
import com.example.demo.dto.request.product.CreateProductVariantRequest;
import com.example.demo.dto.request.warranty.AddWarrantyProductRequest;
import com.example.demo.dto.response.product.CloudinaryResponse;
import com.example.demo.dto.response.product.ProductDetailResponse;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private final ProductsRepository productsRepository;
    private final BrandsRepository brandsRepository;
    private final VariantAttributeRepository variantAttributeRepository;
    private final ProductVariantRepository productVariantRepository;
    private final ProductVariantOptionRepository productVariantOptionRepository;
    private final WarrantyService warrantyService;
    private final SupplierRepository supplierRepository;
    private final ImageService imageService;
    private final ImagesRepository imagesRepository;
    private final CategoryService categoryService;
    private final CategoriesRepository categoriesRepository;

    public ProductService(ProductsRepository productsRepository,
                          BrandsRepository brandsRepository,
                          VariantAttributeRepository variantAttributeRepository,
                          ProductVariantRepository productVariantRepository,
                          ProductVariantOptionRepository productVariantOptionRepository,
                          WarrantyService warrantyService,
                          SupplierRepository supplierRepository,
                          ImageService imageService,
                          ImagesRepository imagesRepository,
                          CategoryService categoryService,
                          CategoriesRepository categoriesRepository) {
        this.productsRepository = productsRepository;
        this.brandsRepository = brandsRepository;
        this.variantAttributeRepository = variantAttributeRepository;
        this.productVariantRepository = productVariantRepository;
        this.productVariantOptionRepository = productVariantOptionRepository;
        this.warrantyService = warrantyService;
        this.supplierRepository = supplierRepository;
        this.imageService = imageService;
        this.imagesRepository = imagesRepository;
        this.categoryService = categoryService;
        this.categoriesRepository = categoriesRepository;
    }

    @Transactional
    public ProductDetailResponse createProduct(CreateProductRequest request) {
        Brands brand = brandsRepository.getReferenceById(request.getBrandId());

        Products product = new Products();
        product.setName(request.getName());
        product.setSlug(request.getSlug());
        product.setArticle(request.getArticle());
        product.setBrand(brand);
        product.setStatus(1L);
        product.setSupplier(supplierRepository.getReferenceById(request.getSupplierId()));
        product.setCategory(categoriesRepository.getReferenceById(request.getCategoryId()));
        Products savedProduct = productsRepository.save(product);

        //add warranty
        for(AddWarrantyProductRequest w : request.getWarranty()) {
            warrantyService.addWarrantyToProduct(w);
        }

        //image
        List<Images> imagesList = new ArrayList<>();
        // 1. thumbnail
        Images image = new Images();
        CloudinaryResponse res = imageService.uploadImage(request.getThumblailImg());
        image.setProduct(savedProduct);
        image.setPath(res.getUrl());
        image.setPublicId(res.getPublicId());
        image.setThumbnail(true);
        Images savedImg = imagesRepository.save(image);
        imagesList.add(savedImg);
        // 2. imgs
        List<CloudinaryResponse> multi = imageService.uploadMultipleImages(request.getImgs());
        for(CloudinaryResponse r : multi) {
            Images images = new Images();
            images.setProduct(savedProduct);
            images.setPath(r.getUrl());
            images.setPublicId(r.getPublicId());
            images.setThumbnail(false);
            Images savedImgs = imagesRepository.save(images);
            imagesList.add(savedImgs);
        }
        savedProduct.setImages(imagesList);

        //cate value
        categoryService.addValue(savedProduct.getProductId(), request.getSpecAttributes());

        // variant attribute
        List<VariantAttribute> varList = new ArrayList<>();
        for(AddAttributeRequest re : request.getVariantAttributes()) {
            VariantAttribute var = addAttributetoProduct(re);
            varList.add(var);
        }
        savedProduct.setVariantAttributeList(varList);

        return ProductDetailResponse.builder()
                .productId(savedProduct.getProductId())
                .productName(savedProduct.getName())
                .brandId(brand.getBrandId())
                .brandName(brand.getName())
                .article(savedProduct.getArticle())
                .slug(savedProduct.getSlug())
                .warranties(new ArrayList<>())
                .attributes(new ArrayList<>())
                .variants(new ArrayList<>())
                .build();
    }


    //variant attribute
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

    public String createProductVariant(CreateProductVariantRequest request) {
        ProductVariant variant = new ProductVariant();
        variant.setProductId(request.getProductId());
        variant.setBuyingPrice(request.getBuyingPrice());
        variant.setSellingPrice(request.getSellingPrice());
        variant.setSku(request.getSku());
        variant.setStockQuantity(0L);
        variant.setSoldQuantity(0L);
        variant.setStatus(1L);
        ProductVariant savedVariant = productVariantRepository.save(variant);

        //tạo product variant option gồm (cặp id, value)
        for(CreateProductVariantRequest.Option options : request.getOptions()) {
            ProductVariantOptionId id = new ProductVariantOptionId(savedVariant.getVariantId(),
                    options.getVariantOptionId());

            ProductVariantOption opt = new ProductVariantOption();
            opt.setId(id);
            opt.setValue(options.getValue());
            opt.setVariant(savedVariant);

            productVariantOptionRepository.save(opt);
        }

        return "Created";
    }

}
