package com.example.demo.service;

import com.example.demo.dto.request.category.AddSpecAttributeToProduct;
import com.example.demo.dto.request.category.CategorySpecRequest;
import com.example.demo.dto.response.category.CategorySpecResponse;
import com.example.demo.entity.Categories;
import com.example.demo.entity.ProductSpecAttribute;
import com.example.demo.entity.ProductSpecAttributeId;
import com.example.demo.entity.SpecAttribute;
import com.example.demo.repository.CategoriesRepository;
import com.example.demo.repository.ProductSpecAttributeRepository;
import jakarta.persistence.Column;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoriesRepository categoriesRepository;
    private final ProductSpecAttributeRepository productSpecAttributeRepository;

    public CategoryService(CategoriesRepository categoriesRepository,
                           ProductSpecAttributeRepository productSpecAttributeRepository) {
        this.categoriesRepository = categoriesRepository;
        this.productSpecAttributeRepository = productSpecAttributeRepository;
    }

    @Transactional
    public CategorySpecResponse createCategorySpec(CategorySpecRequest request) {
        Categories category = new Categories();
        category.setName(request.getName());
        category.setStatus(1L);

        if(request.getSpecs() != null && !request.getSpecs().isEmpty()) {
            List<SpecAttribute> list = new ArrayList<>();
            for(CategorySpecRequest.SpecRequest sp : request.getSpecs()) {
                SpecAttribute sa = new SpecAttribute();
                sa.setCategory(category);
                sa.setName(sp.getName());
                sa.setUnit(sp.getUnit());
                sa.setStatus(sp.getStatus());
                list.add(sa);
            }
            category.setSpecs(list);
        }

        Categories savedCategory = categoriesRepository.save(category);

        CategorySpecResponse response = new CategorySpecResponse();
        response.setCategoryId(savedCategory.getCategoryId());
        response.setName(savedCategory.getName());
        List<CategorySpecResponse.SpecResponse> specres = new ArrayList<>();
        if (savedCategory.getSpecs() != null) {
            for (SpecAttribute sa : savedCategory.getSpecs()) {
                CategorySpecResponse.SpecResponse res = new CategorySpecResponse.SpecResponse();
                res.setAttributeId(sa.getAttributeId());
                res.setCategoryId(savedCategory.getCategoryId());
                res.setName(sa.getName());
                res.setUnit(sa.getUnit());
                specres.add(res);
            }
        }
        response.setSpecs(specres);

        return response;
    }

    @Transactional
    public Categories updateCategoryAndSpecs(Long id, Categories request) {
        Categories category = categoriesRepository.getReferenceById(id);

        // 1. Cập nhật thông tin cơ bản
        category.setName(request.getName());
        category.setStatus(request.getStatus());

        // 2. Đồng bộ Specs
        if (request.getSpecs() != null) {
            // Lấy các ID được gửi lên để giữ lại
            Set<Long> updatedIds = request.getSpecs().stream()
                    .map(SpecAttribute::getAttributeId)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());

            // Xóa các Spec không xuất hiện trong request (Hibernate tự delete DB nhờ orphanRemoval)
            category.getSpecs().removeIf(spec -> !updatedIds.contains(spec.getAttributeId()));

            // Update hoặc Add mới
            for (SpecAttribute specReq : request.getSpecs()) {
                if (specReq.getAttributeId() == null) {
                    // Thêm mới
                    specReq.setCategory(category);
                    category.getSpecs().add(specReq);
                } else {
                    // Sửa cái cũ
                    category.getSpecs().stream()
                            .filter(s -> s.getAttributeId().equals(specReq.getAttributeId()))
                            .findFirst()
                            .ifPresent(existing -> {
                                existing.setName(specReq.getName());
                                existing.setUnit(specReq.getUnit());
                                existing.setStatus(specReq.getStatus());
                            });
                }
            }
        } else {
            category.getSpecs().clear();
        }

        return categoriesRepository.save(category);
    }

    @Transactional
    public void deleteCategory(Long id) {
        categoriesRepository.deleteById(id);
    }

    //add name to product spec attribute
    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "attribute_id", nullable = false)
    private Long attributeId;

    // thêm tên cho product spec_attribute
    public String addValue(Long productId, List<AddSpecAttributeToProduct> request) {
        for(AddSpecAttributeToProduct req : request) {
            ProductSpecAttribute save = new ProductSpecAttribute();
            save.setId(new ProductSpecAttributeId(productId,req.getAttributeId()));
            save.setValue(req.getName());
            productSpecAttributeRepository.save(save);
        }

        return "Saved";
    }
}
