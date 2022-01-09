package com.getir.bookstore.service.impl;

import com.getir.bookstore.domain.Book;
import com.getir.bookstore.dto.request.BookRequestDto;
import com.getir.bookstore.dto.request.PageRequestDto;
import com.getir.bookstore.dto.response.BookDto;
import com.getir.bookstore.dto.response.PageResponseDto;
import com.getir.bookstore.dto.response.ResponseDto;
import com.getir.bookstore.repository.BookRepository;
import com.getir.bookstore.repository.StockRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {BookServiceImpl.class})
@ExtendWith(SpringExtension.class)
class BookServiceImplTest {
    @MockBean
    private BookRepository bookRepository;
    @MockBean
    private StockRepository stockRepository;
    @Autowired
    private BookServiceImpl bookServiceImpl;

    @Test
    void testGetBooks() {

        Book book = new Book();
        book.setAuthor("Sagar");
        book.setPrice(BigDecimal.ZERO);
        book.setDescription("Java is oops langugage");
        book.setCreatedBy("sagar");
        book.setTitle("Java Programming");
        book.setLastModifiedBy("sagar");
        book.setId(1L);
        book.setLastModifiedDate(LocalDateTime.now());
        book.setCreatedDate(LocalDateTime.now());
        List<Book> books = new ArrayList<Book>();
        books.add(book);
        Page<Book> page = Mockito.mock(Page.class);
        when(page.getPageable()).thenReturn(PageRequest.of(0, 10));
        when(page.getContent()).thenReturn(books);
        when(page.getTotalPages()).thenReturn(1);
        when(page.getTotalElements()).thenReturn(1L);
        when(bookRepository.findAll(Mockito.isA(Pageable.class))).thenReturn(page);
        PageRequestDto pageRequestDto = new PageRequestDto();
        pageRequestDto.setPage(0);
        pageRequestDto.setSize(10);
        ResponseDto<PageResponseDto<BookDto>> responseDtoResponseDto = bookServiceImpl.getBooks(pageRequestDto);
        assertNotNull(responseDtoResponseDto);
        assertNotNull(responseDtoResponseDto.getData());
        assertFalse(responseDtoResponseDto.getData().getRecords().isEmpty());
        assertEquals(responseDtoResponseDto.getData().getRecords().size(), 1);
        assertEquals(responseDtoResponseDto.getData().getRecords().get(0).getTitle(), book.getTitle());

    }

    @Test
    void testAddBook() {
        Book book = new Book();
        book.setAuthor("Sagar");
        book.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        book.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book.setDescription("The characteristics of someone or something");
        book.setId(123L);
        book.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book.setPrice(BigDecimal.valueOf(42L));
        book.setTitle("Dr");
        when(this.bookRepository.save((Book) any())).thenReturn(book);

        BookRequestDto bookRequestDto = new BookRequestDto();
        bookRequestDto.setAuthor("Sagar");
        bookRequestDto.setDescription("The characteristics of someone or something");
        bookRequestDto.setPrice(BigDecimal.valueOf(42L));
        bookRequestDto.setTitle("Dr");
        ResponseDto<Boolean> actualAddBookResult = this.bookServiceImpl.addBook(bookRequestDto);
        assertEquals(2000, actualAddBookResult.getCode().intValue());
        assertTrue(actualAddBookResult.getSuccess());
        assertEquals("Record is Saved successfully", actualAddBookResult.getMessage());
        assertNull(actualAddBookResult.getErrors());
        assertNull(actualAddBookResult.getData());
        verify(this.bookRepository).save((Book) any());
    }

    @Test
    void testAddBookFailureCase() {
        Book book = new Book();
        book.setAuthor("Sagar");
        book.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        book.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book.setDescription("The characteristics of someone or something");
        book.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book.setPrice(BigDecimal.valueOf(42L));
        book.setTitle("Dr");
        when(this.bookRepository.save((Book) any())).thenReturn(book);

        BookRequestDto bookRequestDto = new BookRequestDto();
        bookRequestDto.setAuthor("Sagar");
        bookRequestDto.setDescription("The characteristics of someone or something");
        bookRequestDto.setPrice(BigDecimal.valueOf(42L));
        bookRequestDto.setTitle("Dr");
        ResponseDto<Boolean> actualAddBookResult = this.bookServiceImpl.addBook(bookRequestDto);
        assertEquals(1000, actualAddBookResult.getCode().intValue());
        assertFalse(actualAddBookResult.getSuccess());

    }
}

