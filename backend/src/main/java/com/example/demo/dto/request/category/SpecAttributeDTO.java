package com.example.demo.dto.request.category;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SpecAttributeDTO {
    private Long attributeId; // null nếu là thêm mới, có ID nếu là sửa
    private String name;
    private String unit;
    private Long status;

}
