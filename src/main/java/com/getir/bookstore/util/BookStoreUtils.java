package com.getir.bookstore.util;

import com.getir.bookstore.dto.response.PageDto;
import org.springframework.data.domain.Page;

public class BookStoreUtils {
    public static PageDto getPageDto(Page page) {
        return PageDto.builder()
                .actualRecords(page.getNumberOfElements())
                .totalPages(page.getTotalPages())
                .size(page.getSize())
                .page(page.getPageable().getPageNumber())
                .totalRecords(page.getTotalElements())
                .build();
    }
}
