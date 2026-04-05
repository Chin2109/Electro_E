package com.example.demo.dto.request.category;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryDTO {
    private Long categoryId;
    private String name;
    private Long status;
    private List<SpecAttributeDTO> specs;

}
