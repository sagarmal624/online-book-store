package com.getir.bookstore.service.impl;

import com.getir.bookstore.domain.Book;
import com.getir.bookstore.dto.request.BookRequestDto;
import com.getir.bookstore.dto.request.PageRequestDto;
import com.getir.bookstore.dto.response.BookDto;
import com.getir.bookstore.dto.response.PageDto;
import com.getir.bookstore.dto.response.PageResponseDto;
import com.getir.bookstore.dto.response.ResponseDto;
import com.getir.bookstore.repository.BookRepository;
import com.getir.bookstore.service.BookService;
import com.getir.bookstore.util.BookStoreUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public ResponseDto<Boolean> addBook(BookRequestDto request) {
        Book book = bookRepository.save(convertDtoToEntity(request));
        return Objects.nonNull(book.getId()) ? ResponseDto.buildSuccess() : ResponseDto.buildError();
    }

    @Override
    public ResponseDto<PageResponseDto<BookDto>> getBooks(PageRequestDto pageRequestDto) {
        return ResponseDto.buildSuccess(getBooksFromDb(pageRequestDto));
    }

    private PageResponseDto<BookDto> getBooksFromDb(PageRequestDto pageRequestDto) {
        Pageable pageable = PageRequest.of(pageRequestDto.getPage(), pageRequestDto.getSize());
        Page<Book> page =  bookRepository.findAll(pageable);
        List<Book> books = page.getContent();
        return PageResponseDto.<BookDto>builder().records(getBookDtoList(books)).page(BookStoreUtils.getPageDto(page)).build();
    }



    private List<BookDto> getBookDtoList(List<Book> books) {
        return books.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    private BookDto convertEntityToDto(Book book) {
        BookDto bookDto = new BookDto();
        BeanUtils.copyProperties(book, bookDto);
        return bookDto;
    }

    private Book convertDtoToEntity(BookRequestDto requestDto) {
        Book book = new Book();
        BeanUtils.copyProperties(requestDto, book);
        return book;
    }
}
