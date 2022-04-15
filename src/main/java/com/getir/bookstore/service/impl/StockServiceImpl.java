package com.getir.bookstore.service.impl;

import com.getir.bookstore.constant.enums.BookStoreErrorCode;
import com.getir.bookstore.domain.Book;
import com.getir.bookstore.domain.Stock;
import com.getir.bookstore.dto.request.PageRequestDto;
import com.getir.bookstore.dto.request.StockRequestDto;
import com.getir.bookstore.dto.response.PageResponseDto;
import com.getir.bookstore.dto.response.ResponseDto;
import com.getir.bookstore.dto.response.StockDto;
import com.getir.bookstore.exception.RecordNotFoundException;
import com.getir.bookstore.repository.BookRepository;
import com.getir.bookstore.repository.StockRepository;
import com.getir.bookstore.service.StockService;
import com.getir.bookstore.util.BookStoreUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StockServiceImpl implements StockService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private StockRepository stockRepository;

    @Override
    public ResponseDto<StockDto> updateBookOfStock(StockRequestDto stockRequestDto) {
        Optional<Book> bookOptional = bookRepository.findById(stockRequestDto.getBookId());
        Book book = bookOptional.orElseThrow(() ->
                new RecordNotFoundException(BookStoreErrorCode.BOOK_NOT_FOUND.getMessage(),"bookId")
        );
        Optional<Stock> dbStockOptional = stockRepository.findByBook_Id(stockRequestDto.getBookId());
        Stock stock = dbStockOptional.orElse(null);
        if (Objects.isNull(stock)) {
            stock = new Stock();
            stock.setBook(book);
            stock.setQuantity(stockRequestDto.getQuantity());
        }else{
            stock.setQuantity(stock.getQuantity()+stockRequestDto.getQuantity());
        }
        stock = stockRepository.save(stock);
        return Objects.nonNull(stock.getId()) ? ResponseDto.buildSuccess() : ResponseDto.buildError();
    }

    @Override
    public ResponseDto<PageResponseDto<StockDto>> getStocks(PageRequestDto pageRequestDto) {
        return ResponseDto.buildSuccess(getStocksFromDb(pageRequestDto));
    }

    @Override
    public ResponseDto<StockDto> getStockByBookId(Long bookId) {
        Optional<Stock> stockOptional = stockRepository.findByBook_Id(bookId);
        Stock stock = stockOptional.orElseThrow(() -> new RecordNotFoundException(BookStoreErrorCode.STOCK_NOT_FOUND.getMessage(),"stockId"));
        StockDto stockDto = convertEntityToDto(stock);
        return ResponseDto.buildSuccess(stockDto);
    }

    private PageResponseDto<StockDto> getStocksFromDb(PageRequestDto pageRequestDto) {
        Pageable pageable = PageRequest.of(pageRequestDto.getPage(), pageRequestDto.getSize());
        Page<Stock> page = stockRepository.findAll(pageable);
        List<Stock> stocks = page.getContent();
        return PageResponseDto.<StockDto>builder().records(getStockDtoList(stocks)).page(BookStoreUtils.getPageDto(page)).build();
    }

    private List<StockDto> getStockDtoList(List<Stock> stocks) {
        if (CollectionUtils.isEmpty(stocks)) {
            return new ArrayList<>();
        }
        return stocks.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    private StockDto convertEntityToDto(Stock stock) {
        StockDto stockDto = new StockDto();
        stockDto.setBookId(stock.getBook().getId());
        stockDto.setStockId(stock.getId());
        stockDto.setQuantity(stock.getQuantity());
        return stockDto;
    }
}
