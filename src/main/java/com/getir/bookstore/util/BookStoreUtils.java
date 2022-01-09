package com.getir.bookstore.util;

import com.getir.bookstore.dto.response.PageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
@Slf4j
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
