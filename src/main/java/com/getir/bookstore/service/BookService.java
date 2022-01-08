package com.getir.bookstore.service;

import com.getir.bookstore.dto.request.BookRequestDto;
import com.getir.bookstore.dto.request.PageRequestDto;
import com.getir.bookstore.dto.response.BookDto;
import com.getir.bookstore.dto.response.PageResponseDto;
import com.getir.bookstore.dto.response.ResponseDto;

public interface BookService {

    ResponseDto<Boolean> addBook(BookRequestDto request);

    ResponseDto<PageResponseDto<BookDto>> getBooks(PageRequestDto pageRequestDto);


}
