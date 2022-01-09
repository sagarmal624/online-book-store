package com.getir.bookstore.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class PageResponseDto<T> {
    private long totalRecords;
    private int totalPages;
    private int actualRecords;
    private int page;
    private int size;
    private List<T> records;

    @Builder
    public PageResponseDto(PageDto page, List<T> records) {
        this.totalPages = page.getTotalPages();
        this.totalRecords = page.getTotalRecords();
        this.actualRecords = page.getActualRecords();
        this.page = page.getPage();
        this.size = page.getSize();
        this.records = records;
    }
}
