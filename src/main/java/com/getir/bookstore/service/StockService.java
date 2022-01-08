package com.getir.bookstore.service;

import com.getir.bookstore.dto.request.PageRequestDto;
import com.getir.bookstore.dto.request.StockRequestDto;
import com.getir.bookstore.dto.response.PageResponseDto;
import com.getir.bookstore.dto.response.ResponseDto;
import com.getir.bookstore.dto.response.StockDto;


public interface StockService {
    ResponseDto<StockDto> updateBookOfStock(StockRequestDto request);

    ResponseDto<PageResponseDto<StockDto>> getStocks(PageRequestDto pageRequestDto);

    ResponseDto<StockDto> getStockByBookId(Long bookId);

}
