package com.example.demo.service;

import com.example.demo.dto.request.category.CategorySpecRequest;
import com.example.demo.dto.response.category.CategorySpecResponse;
import com.example.demo.entity.Categories;
import com.example.demo.entity.SpecAttribute;
import com.example.demo.repository.CategoriesRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    private final CategoriesRepository categoriesRepository;

    public CategoryService(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
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
                sa.setStatus(1L);
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
}
