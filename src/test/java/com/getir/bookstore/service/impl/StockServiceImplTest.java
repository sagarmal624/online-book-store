package com.getir.bookstore.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.getir.bookstore.domain.Book;
import com.getir.bookstore.domain.Stock;
import com.getir.bookstore.dto.request.PageRequestDto;
import com.getir.bookstore.dto.request.StockRequestDto;
import com.getir.bookstore.dto.response.ResponseDto;
import com.getir.bookstore.dto.response.StockDto;
import com.getir.bookstore.exception.RecordNotFoundException;
import com.getir.bookstore.repository.BookRepository;
import com.getir.bookstore.repository.StockRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {StockServiceImpl.class})
@ExtendWith(SpringExtension.class)
class StockServiceImplTest {
    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private StockRepository stockRepository;

    @Autowired
    private StockServiceImpl stockServiceImpl;

    @Test
    void testUpdateBookOfStock() {
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

        Stock stock = new Stock();
        stock.setBook(book);
        stock.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        stock.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        stock.setId(123L);
        stock.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        stock.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        stock.setQuantity(1);
        Optional<Stock> ofResult = Optional.of(stock);

        Book book1 = new Book();
        book1.setAuthor("JaneDoe");
        book1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        book1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book1.setDescription("The characteristics of someone or something");
        book1.setId(123L);
        book1.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        book1.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book1.setPrice(BigDecimal.valueOf(42L));
        book1.setTitle("Dr");

        Stock stock1 = new Stock();
        stock1.setBook(book1);
        stock1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        stock1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        stock1.setId(123L);
        stock1.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        stock1.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        stock1.setQuantity(1);
        when(this.stockRepository.save((Stock) any())).thenReturn(stock1);
        when(this.stockRepository.findByBook_Id((Long) any())).thenReturn(ofResult);

        Book book2 = new Book();
        book2.setAuthor("JaneDoe");
        book2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        book2.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book2.setDescription("The characteristics of someone or something");
        book2.setId(123L);
        book2.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        book2.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book2.setPrice(BigDecimal.valueOf(42L));
        book2.setTitle("Dr");
        Optional<Book> ofResult1 = Optional.of(book2);
        when(this.bookRepository.findById((Long) any())).thenReturn(ofResult1);
        StockRequestDto stockRequestDto = mock(StockRequestDto.class);
        when(stockRequestDto.getQuantity()).thenReturn(1);
        when(stockRequestDto.getBookId()).thenReturn(123L);
        ResponseDto<StockDto> actualUpdateBookOfStockResult = this.stockServiceImpl.updateBookOfStock(stockRequestDto);
        assertEquals(2000, actualUpdateBookOfStockResult.getCode().intValue());
        assertTrue(actualUpdateBookOfStockResult.getSuccess());
        assertEquals("Record is Saved successfully", actualUpdateBookOfStockResult.getMessage());
        assertNull(actualUpdateBookOfStockResult.getErrors());
        assertNull(actualUpdateBookOfStockResult.getData());
        verify(this.stockRepository).findByBook_Id((Long) any());
        verify(this.stockRepository).save((Stock) any());
        verify(this.bookRepository).findById((Long) any());
        verify(stockRequestDto, atLeast(1)).getBookId();
        verify(stockRequestDto).getQuantity();
    }

    @Test
    void testUpdateBookOfStock2() {
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

        Stock stock = new Stock();
        stock.setBook(book);
        stock.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        stock.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        stock.setId(123L);
        stock.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        stock.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        stock.setQuantity(1);
        Optional<Stock> ofResult = Optional.of(stock);
        when(this.stockRepository.save((Stock) any()))
                .thenThrow(new RecordNotFoundException("An error occurred", "Field Name"));
        when(this.stockRepository.findByBook_Id((Long) any())).thenReturn(ofResult);

        Book book1 = new Book();
        book1.setAuthor("JaneDoe");
        book1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        book1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book1.setDescription("The characteristics of someone or something");
        book1.setId(123L);
        book1.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        book1.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book1.setPrice(BigDecimal.valueOf(42L));
        book1.setTitle("Dr");
        Optional<Book> ofResult1 = Optional.of(book1);
        when(this.bookRepository.findById((Long) any())).thenReturn(ofResult1);
        StockRequestDto stockRequestDto = mock(StockRequestDto.class);
        when(stockRequestDto.getQuantity()).thenReturn(1);
        when(stockRequestDto.getBookId()).thenReturn(123L);
        assertThrows(RecordNotFoundException.class, () -> this.stockServiceImpl.updateBookOfStock(stockRequestDto));
        verify(this.stockRepository).findByBook_Id((Long) any());
        verify(this.stockRepository).save((Stock) any());
        verify(this.bookRepository).findById((Long) any());
        verify(stockRequestDto, atLeast(1)).getBookId();
        verify(stockRequestDto).getQuantity();
    }

    @Test
    void testGetStocks() {
        when(this.stockRepository.findAll((org.springframework.data.domain.Pageable) any()))
                .thenThrow(new RecordNotFoundException("An error occurred", "Field Name"));

        PageRequestDto pageRequestDto = new PageRequestDto();
        pageRequestDto.setPage(1);
        pageRequestDto.setSize(3);
        assertThrows(RecordNotFoundException.class, () -> this.stockServiceImpl.getStocks(pageRequestDto));
        verify(this.stockRepository).findAll((org.springframework.data.domain.Pageable) any());
    }

    @Test
    void testGetStockByBookId() {
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

        Stock stock = new Stock();
        stock.setBook(book);
        stock.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        stock.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        stock.setId(123L);
        stock.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        stock.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        stock.setQuantity(1);
        Optional<Stock> ofResult = Optional.of(stock);
        when(this.stockRepository.findByBook_Id((Long) any())).thenReturn(ofResult);
        ResponseDto<StockDto> actualStockByBookId = this.stockServiceImpl.getStockByBookId(123L);
        assertEquals(2000, actualStockByBookId.getCode().intValue());
        assertEquals(
                "ResponseDto(code=2000, message=null, success=true, data=StockDto(bookId=123, quantity=1, stockId=123),"
                        + " errors=null)",
                actualStockByBookId.toString());
        assertTrue(actualStockByBookId.getSuccess());
        assertNull(actualStockByBookId.getMessage());
        assertNull(actualStockByBookId.getErrors());
        StockDto data = actualStockByBookId.getData();
        assertEquals(123L, data.getBookId().longValue());
        assertEquals(123L, data.getStockId().longValue());
        assertEquals(1, data.getQuantity().intValue());
        verify(this.stockRepository).findByBook_Id((Long) any());
    }

    @Test
    void testGetStockByBookId2() {
        when(this.stockRepository.findByBook_Id((Long) any()))
                .thenThrow(new RecordNotFoundException("An error occurred", "Field Name"));
        assertThrows(RecordNotFoundException.class, () -> this.stockServiceImpl.getStockByBookId(123L));
        verify(this.stockRepository).findByBook_Id((Long) any());
    }

    @Test
    void testGetStockByBookId3() {
        when(this.stockRepository.findByBook_Id((Long) any())).thenReturn(Optional.empty());
        assertThrows(RecordNotFoundException.class, () -> this.stockServiceImpl.getStockByBookId(123L));
        verify(this.stockRepository).findByBook_Id((Long) any());
    }
}

