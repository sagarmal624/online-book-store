package com.getir.bookstore.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.getir.bookstore.constant.ApiEndPoint;
import com.getir.bookstore.dto.request.BookRequestDto;
import com.getir.bookstore.dto.response.BookDto;
import com.getir.bookstore.dto.response.PageDto;
import com.getir.bookstore.dto.response.PageResponseDto;
import com.getir.bookstore.dto.response.ResponseDto;
import com.getir.bookstore.service.BookService;
import com.getir.bookstore.validator.BookValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {BookController.class})
@ExtendWith(SpringExtension.class)
public class BookControllerTest {
    @MockBean
    private BookService bookService;
    @MockBean
    private BookValidator BookValidator;

    @Autowired
    private BookController BookController;

    @Test
    void testAddBookSuccess() throws Exception {
        ResponseDto<Boolean> responseDto = ResponseDto.buildSuccess();
        when(bookService.addBook(any())).thenReturn(responseDto);
        BookRequestDto bookRequestDto = new BookRequestDto();
        bookRequestDto.setAuthor("Sagar");
        bookRequestDto.setTitle("Java Programing Book");
        bookRequestDto.setPrice(BigDecimal.ZERO);
        bookRequestDto.setDescription("java is a Object Oriented programming so Read Java and enjoy");
        String content = (new ObjectMapper()).writeValueAsString(bookRequestDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(ApiEndPoint.BOOK_BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.BookController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }

    @Test
    void testAddBookFailure() throws Exception {
        ResponseDto<Boolean> responseDto = ResponseDto.buildSuccess();
        when(bookService.addBook(any())).thenReturn(responseDto);
        BookRequestDto bookRequestDto = new BookRequestDto();
        String content = (new ObjectMapper()).writeValueAsString(bookRequestDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(ApiEndPoint.BOOK_BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.BookController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }

    @Test
    void testGetBooksSuccess() throws Exception {

        BookDto bookDto = new BookDto();
        bookDto.setAuthor("Sagar");
        bookDto.setTitle("Java Programing Book");
        bookDto.setPrice(BigDecimal.ZERO);
        bookDto.setDescription("java is a Object Oriented programming so Read Java and enjoy");
        PageDto pageDto = PageDto.builder().page(0).size(10).build();
        List<BookDto> bookDtoList = new ArrayList<>();
        bookDtoList.add(bookDto);
        ResponseDto<PageResponseDto<BookDto>> responseDtoResponseDto = ResponseDto.buildSuccess(PageResponseDto.<BookDto>builder().page(pageDto).records(bookDtoList).build());
        when(bookService.getBooks(any())).thenReturn(responseDtoResponseDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(ApiEndPoint.BOOK_BASE_URL + "?page=0&size=10")
                .contentType(MediaType.APPLICATION_JSON);
        MockMvcBuilders.standaloneSetup(this.BookController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetBooksFailure() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(ApiEndPoint.BOOK_BASE_URL)
                .contentType(MediaType.APPLICATION_JSON);
        MockMvcBuilders.standaloneSetup(this.BookController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }

    @Test
    void testGetBooksURLNotFound() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(ApiEndPoint.BOOK_BASE_URL + "/test")
                .contentType(MediaType.APPLICATION_JSON);
        MockMvcBuilders.standaloneSetup(this.BookController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
