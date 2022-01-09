package com.getir.bookstore.controller;

import com.getir.bookstore.constant.ApiEndPoint;
import com.getir.bookstore.dto.request.BookRequestDto;
import com.getir.bookstore.dto.request.PageRequestDto;
import com.getir.bookstore.dto.response.ResponseDto;
import com.getir.bookstore.service.BookService;
import com.getir.bookstore.validator.BookValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(ApiEndPoint.BOOK_BASE_URL)
@Slf4j
public class BookController extends BaseController {
    @Autowired
    private BookService bookService;

    @Autowired
    private BookValidator bookValidator;

    @PostMapping
    public ResponseEntity<ResponseDto> addBook(@Valid @RequestBody BookRequestDto bookRequestDto, BindingResult bindingResult) {
        bookValidator.validate(bookRequestDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(getErrors(bindingResult));
        }
        return ResponseEntity.ok(bookService.addBook(bookRequestDto));
    }

    @GetMapping
    public ResponseEntity<ResponseDto> getBooks(@Valid PageRequestDto pageRequestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(getErrors(bindingResult));
        }
        return ResponseEntity.ok(bookService.getBooks(pageRequestDto));
    }

}
