package com.getir.bookstore.validator;

import com.getir.bookstore.domain.Book;
import com.getir.bookstore.dto.request.BookRequestDto;
import com.getir.bookstore.repository.BookRepository;
import com.getir.bookstore.util.BeanUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.BindException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ContextConfiguration(classes = {BookValidator.class})
@ExtendWith(SpringExtension.class)
class BookValidatorTest {
    @MockBean
    BookRepository bookRepository;

    @BeforeAll
    static void init() {
        Mockito.mockStatic(BeanUtil.class);
    }

    @Test
    void testSupports() {
        BookValidator bookValidator = new BookValidator();
        assertFalse(bookValidator.supports(Object.class));
    }

    @Test
    void testValidate() {
        BookValidator bookValidator = new BookValidator();
        Book book = new Book();
        book.setAuthor("sagar");
        book.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        book.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book.setDescription("The characteristics of someone or something");
        book.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book.setPrice(BigDecimal.valueOf(42L));
        book.setTitle("Java Programming");
        BookRequestDto bookRequestDto = new BookRequestDto();
        bookRequestDto.setAuthor("sagar");
        bookRequestDto.setDescription("The characteristics of someone or something");
        bookRequestDto.setPrice(BigDecimal.valueOf(42L));
        bookRequestDto.setTitle("Java Programming");

        Mockito.when(BeanUtil.getBean(Mockito.any())).thenReturn(bookRepository);
        Mockito.when(bookRepository.findByTitle(Mockito.any())).thenReturn(Optional.of(book));
        bookValidator.validate(bookRequestDto,
                new BindException(bookRequestDto, "com.getir.bookstore.dto.request.BookRequestDto"));
        assertEquals("sagar", bookRequestDto.getAuthor());
        assertEquals("Java Programming", bookRequestDto.getTitle());
        assertEquals("The characteristics of someone or something", bookRequestDto.getDescription());
    }

    @Test
    void testValidateWithSuccessCase() {
        BookValidator bookValidator = new BookValidator();
        Book book = new Book();
        book.setAuthor("sagar");
        book.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        book.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book.setDescription("The characteristics of someone or something");
        book.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book.setPrice(BigDecimal.valueOf(42L));
        book.setTitle("");
        BookRequestDto bookRequestDto = new BookRequestDto();
        bookRequestDto.setAuthor("sagar");
        bookRequestDto.setDescription("The characteristics of someone or something");
        bookRequestDto.setPrice(BigDecimal.valueOf(42L));
        bookRequestDto.setTitle("");
        Mockito.when(BeanUtil.getBean(Mockito.any())).thenReturn(bookRepository);
        Mockito.when(bookRepository.findByTitle(Mockito.any())).thenReturn(Optional.of(book));
        bookValidator.validate(bookRequestDto,
                new BindException(bookRequestDto, "com.getir.bookstore.dto.request.BookRequestDto"));
        assertEquals("sagar", bookRequestDto.getAuthor());
        assertEquals("", bookRequestDto.getTitle());
        assertEquals("The characteristics of someone or something", bookRequestDto.getDescription());
    }
    @Test
    void testValidateWithEmptyBookRecordCase() {
        BookValidator bookValidator = new BookValidator();
        BookRequestDto bookRequestDto = new BookRequestDto();
        bookRequestDto.setAuthor("sagar");
        bookRequestDto.setDescription("The characteristics of someone or something");
        bookRequestDto.setPrice(BigDecimal.valueOf(42L));
        bookRequestDto.setTitle("");
        Mockito.when(BeanUtil.getBean(Mockito.any())).thenReturn(bookRepository);
        Mockito.when(bookRepository.findByTitle(Mockito.any())).thenReturn(Optional.empty());
        bookValidator.validate(bookRequestDto,
                new BindException(bookRequestDto, "com.getir.bookstore.dto.request.BookRequestDto"));
        assertEquals("sagar", bookRequestDto.getAuthor());
        assertEquals("", bookRequestDto.getTitle());
        assertEquals("The characteristics of someone or something", bookRequestDto.getDescription());
    }
}

