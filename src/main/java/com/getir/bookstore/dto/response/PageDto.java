package com.getir.bookstore.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PageDto {
    private long totalRecords;
    private int totalPages;
    private int actualRecords;
    private int page;
    private int size;
}
