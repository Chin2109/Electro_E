package com.example.demo.dto.response.category;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategorySpecResponse {
    Long categoryId;
    String name;
    List<SpecResponse> specs;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class SpecResponse {
        Long attributeId;
        Long categoryId;
        String name;
    }
}