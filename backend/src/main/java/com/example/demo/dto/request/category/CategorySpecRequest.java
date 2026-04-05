package com.example.demo.dto.request.category;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategorySpecRequest {
    String name;
    List<SpecRequest> specs;
    Long status;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class SpecRequest {
        String name;
        String unit;
        Long status;
    }
}
