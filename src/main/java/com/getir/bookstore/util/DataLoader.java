package com.getir.bookstore.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.getir.bookstore.domain.Book;
import com.getir.bookstore.domain.Stock;
import com.getir.bookstore.dto.request.BookRequestDto;
import com.getir.bookstore.repository.BookRepository;
import com.getir.bookstore.repository.StockRepository;
import com.getir.bookstore.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class DataLoader {

    @Autowired
    private BookService bookService;
    @Autowired
    StockRepository stockRepository;
    @Autowired
    BookRepository bookRepository;

    @PostConstruct
    public void init() {
        if (bookRepository.count() == 0) {
            //createBooks();
        }
        if (stockRepository.count() == 0) {
            updateStockForBooks();
        }
    }

    public void createBooks() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            BookRequestDto[] bookRequestDtos = objectMapper.readValue(new File("src/main/resources/books.json"), BookRequestDto[].class);
            for (BookRequestDto bookRequestDto : bookRequestDtos) {
                bookService.addBook(bookRequestDto);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    public void updateStockForBooks() {
        try {
            List<Book> books = bookRepository.findAll();
            books.stream().forEach(book -> {
                Stock stock = new Stock();
                stock.setBook(book);
                stock.setQuantity(new Random().nextInt(5000));
                stockRepository.save(stock);
            });

        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }
}
