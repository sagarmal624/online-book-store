package com.getir.bookstore.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.getir.bookstore.dto.request.BookRequestDto;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.validation.BindException;

class BookValidatorTest {
    @Test
    void testSupports() {
        BookValidator bookValidator = new BookValidator();
        assertFalse(bookValidator.supports(Object.class));
    }

    @Test
    void testSupports2() {
        BookValidator bookValidator = new BookValidator();
        assertFalse(bookValidator.supports(Object.class));
    }

    @Test
    void testValidate() {
        BookValidator bookValidator = new BookValidator();

        BookRequestDto bookRequestDto = new BookRequestDto();
        bookRequestDto.setAuthor("JaneDoe");
        bookRequestDto.setDescription("The characteristics of someone or something");
        bookRequestDto.setPrice(BigDecimal.valueOf(42L));
        bookRequestDto.setTitle("");
        bookValidator.validate(bookRequestDto,
                new BindException(bookRequestDto, "com.getir.bookstore.dto.request.BookRequestDto"));
        assertEquals("JaneDoe", bookRequestDto.getAuthor());
        assertEquals("BookRequestDto(title=, author=JaneDoe, description=The characteristics of someone or something,"
                + " price=42)", bookRequestDto.toString());
        assertEquals("", bookRequestDto.getTitle());
        assertEquals("The characteristics of someone or something", bookRequestDto.getDescription());
    }

    @Test
    void testValidate2() {
        BookValidator bookValidator = new BookValidator();

        BookRequestDto bookRequestDto = new BookRequestDto();
        bookRequestDto.setAuthor("JaneDoe");
        bookRequestDto.setDescription("The characteristics of someone or something");
        bookRequestDto.setPrice(BigDecimal.valueOf(42L));
        bookRequestDto.setTitle("");
        bookValidator.validate(bookRequestDto,
                new BindException(bookRequestDto, "com.getir.bookstore.dto.request.BookRequestDto"));
        assertEquals("JaneDoe", bookRequestDto.getAuthor());
        assertEquals("BookRequestDto(title=, author=JaneDoe, description=The characteristics of someone or something,"
                + " price=42)", bookRequestDto.toString());
        assertEquals("", bookRequestDto.getTitle());
        assertEquals("The characteristics of someone or something", bookRequestDto.getDescription());
    }
}

