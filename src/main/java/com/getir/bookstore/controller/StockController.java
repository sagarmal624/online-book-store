package com.getir.bookstore.controller;

import com.getir.bookstore.constant.ApiEndPoint;
import com.getir.bookstore.dto.request.PageRequestDto;
import com.getir.bookstore.dto.request.StockRequestDto;
import com.getir.bookstore.dto.response.ResponseDto;
import com.getir.bookstore.dto.response.StockDto;
import com.getir.bookstore.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(ApiEndPoint.STOCK_BASE_URL)
@RequiredArgsConstructor
@Slf4j
public class StockController extends BaseController {
    @Autowired
    private StockService stockService;

    @PutMapping("/book/{bookId}")
    public ResponseEntity<ResponseDto> updateStockQuantity(@PathVariable("bookId") Long bookId, @RequestBody @Valid StockRequestDto stockRequestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(getErrors(bindingResult));
        }
        stockRequestDto.setBookId(bookId);
        return ResponseEntity.ok(stockService.updateBookOfStock(stockRequestDto));
    }

    @GetMapping("/books")
    public ResponseEntity<ResponseDto> getStocks(@Valid PageRequestDto pageRequestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(getErrors(bindingResult));
        }
        return ResponseEntity.ok(stockService.getStocks(pageRequestDto));
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<ResponseDto<StockDto>> findStockByBookId(@PathVariable("bookId") Long bookId) {
        return ResponseEntity.ok(stockService.getStockByBookId(bookId));
    }

}
