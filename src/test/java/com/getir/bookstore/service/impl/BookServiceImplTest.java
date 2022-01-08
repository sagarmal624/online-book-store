package com.getir.bookstore.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.getir.bookstore.domain.Book;
import com.getir.bookstore.dto.request.BookRequestDto;
import com.getir.bookstore.dto.response.ResponseDto;
import com.getir.bookstore.repository.BookRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {BookServiceImpl.class})
@ExtendWith(SpringExtension.class)
class BookServiceImplTest {
    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private BookServiceImpl bookServiceImpl;

    @Test
    void testAddBook() {
        Book book = new Book();
        book.setAuthor("JaneDoe");
        book.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        book.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book.setDescription("The characteristics of someone or something");
        book.setId(123L);
        book.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        book.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book.setPrice(BigDecimal.valueOf(42L));
        book.setTitle("Dr");
        when(this.bookRepository.save((Book) any())).thenReturn(book);

        BookRequestDto bookRequestDto = new BookRequestDto();
        bookRequestDto.setAuthor("JaneDoe");
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
}

