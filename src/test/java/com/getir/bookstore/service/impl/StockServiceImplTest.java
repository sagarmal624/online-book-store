package com.getir.bookstore.service.impl;

import com.getir.bookstore.domain.Book;
import com.getir.bookstore.domain.Stock;
import com.getir.bookstore.dto.request.StockRequestDto;
import com.getir.bookstore.dto.response.ResponseDto;
import com.getir.bookstore.dto.response.StockDto;
import com.getir.bookstore.exception.RecordNotFoundException;
import com.getir.bookstore.repository.BookRepository;
import com.getir.bookstore.repository.StockRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Optional;

@ContextConfiguration(classes = {StockServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class StockServiceImplTest {
    @MockBean
    private BookRepository bookRepository;
    @MockBean
    private StockRepository stockRepository;

    @Autowired
    private StockServiceImpl stockServiceImpl;

    @Test
    public void testUpdateBookOfStock_WhenBookExist() {
        Book book = Book.builder().title("Java").price(new BigDecimal(100)).author("sagar").description("java Programming Worlkd").build();
        book.setId(1L);
        Mockito.when(bookRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(book));
        Stock stock = new Stock();
        stock.setBook(book);
        stock.setId(1L);
        stock.setQuantity(20);

        Mockito.when(stockRepository.findByBook_Id(Mockito.anyLong())).thenReturn(Optional.of(stock));
        StockRequestDto stockRequestDto = new StockRequestDto();
        stockRequestDto.setQuantity(10);
        stockRequestDto.setBookId(1L);

        Mockito.when(stockRepository.save(Mockito.any(Stock.class))).thenReturn(stock);
        ResponseDto<StockDto> responseDto = stockServiceImpl.updateBookOfStock(stockRequestDto);

        Assertions.assertEquals(responseDto.getSuccess(), true);
        Assertions.assertEquals(responseDto.getMessage(), "Record is Saved successfully");


    }

    @Test
    public void testUpdateBookOfStock_WhenBookExistAndStockDoesNotExist() {
        Book book = Book.builder().title("Java").price(new BigDecimal(100)).author("sagar").description("java Programming Worlkd").build();
        book.setId(1L);
        Mockito.when(bookRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(book));
        Stock stock = new Stock();
        stock.setBook(book);
        stock.setId(1L);
        stock.setQuantity(20);

        Mockito.when(stockRepository.findByBook_Id(Mockito.anyLong())).thenReturn(Optional.empty());
        StockRequestDto stockRequestDto = new StockRequestDto();
        stockRequestDto.setQuantity(10);
        stockRequestDto.setBookId(1L);

        Mockito.when(stockRepository.save(Mockito.any(Stock.class))).thenReturn(stock);
        ResponseDto<StockDto> responseDto = stockServiceImpl.updateBookOfStock(stockRequestDto);

        Assertions.assertEquals(responseDto.getSuccess(), true);
        Assertions.assertEquals(responseDto.getMessage(), "Record is Saved successfully");
    }

    @Test
    public void testUpdateBookOfStock_WhenBookDoesNotExist() {
        Mockito.when(bookRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        StockRequestDto stockRequestDto = new StockRequestDto();
        stockRequestDto.setQuantity(10);
        stockRequestDto.setBookId(1L);
        RecordNotFoundException recordNotFoundException = Assertions.assertThrows(RecordNotFoundException.class, () -> {
            stockServiceImpl.updateBookOfStock(stockRequestDto);

        });

        Assertions.assertEquals(recordNotFoundException.getMessage(), "Book Record is not found in DB");
    }
}
