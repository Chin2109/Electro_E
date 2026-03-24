package com.example.demo.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageResponse<T> {
    private String message;
    private List<T> data;
    private int page;          // Số trang hiện tại (thường bắt đầu từ 0 hoặc 1 tùy quy ước)
    private int size;          // Số phần tử trên một trang
    private long totalElements; // Tổng số bản ghi trong DB
    private int totalPages;    // Tổng số trang
    private boolean last;      // Có phải trang cuối cùng không

    public static <T> PageResponse<T> fromPage(Page<T> page) {
        return PageResponse.<T>builder()
                .data(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }
}
