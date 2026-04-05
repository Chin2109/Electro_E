package com.example.demo.dto.request.product;

import com.example.demo.dto.request.category.AddSpecAttributeToProduct;
import com.example.demo.dto.request.warranty.AddWarrantyProductRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateProductRequest {
    Long brandId;

    String slug;

    String name;

    String article;     //html article include video youtube

    MultipartFile thumblailImg;

    List<MultipartFile> imgs;

    List<AddWarrantyProductRequest> warranty;

    Long supplierId;

    Long categoryId;

    List<AddSpecAttributeToProduct> specAttributes;
    List<AddAttributeRequest> variantAttributes;
}
