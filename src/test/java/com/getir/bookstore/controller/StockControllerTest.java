package com.getir.bookstore.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.getir.bookstore.constant.ApiEndPoint;
import com.getir.bookstore.dto.request.StockRequestDto;
import com.getir.bookstore.dto.response.PageDto;
import com.getir.bookstore.dto.response.PageResponseDto;
import com.getir.bookstore.dto.response.ResponseDto;
import com.getir.bookstore.dto.response.StockDto;
import com.getir.bookstore.service.StockService;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {StockController.class})
@ExtendWith(SpringExtension.class)
public class StockControllerTest {
    @MockBean
    private StockService stockService;

    @Autowired
    private StockController StockController;

    @Test
    void testAddStockSuccess() throws Exception {
        ResponseDto<StockDto> responseDto = ResponseDto.buildSuccess();
        when(stockService.updateBookOfStock(any())).thenReturn(responseDto);
        StockRequestDto stockRequestDto = StockRequestDto.builder().bookId(1L).quantity(10).build();

        String content = (new ObjectMapper()).writeValueAsString(stockRequestDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put(ApiEndPoint.STOCK_BASE_URL + "/book/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.StockController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }

    @Test
    void testAddStockFailureWithNullQuantity() throws Exception {
        StockRequestDto stockRequestDto = StockRequestDto.builder().quantity(null).build();
        String content = (new ObjectMapper()).writeValueAsString(stockRequestDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put(ApiEndPoint.STOCK_BASE_URL + "/book/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.StockController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }


    @Test
    void testGetStocksSuccess() throws Exception {
        StockDto stockDto = new StockDto();
        stockDto.setStockId(1L);
        stockDto.setBookId(2L);
        stockDto.setQuantity(100);
        PageDto pageDto = PageDto.builder().page(0).size(10).build();
        List<StockDto> stockDtoList = new ArrayList<>();
        stockDtoList.add(stockDto);
        ResponseDto<PageResponseDto<StockDto>> responseDtoResponseDto = ResponseDto.buildSuccess(PageResponseDto.<StockDto>builder().page(pageDto).records(stockDtoList).build());
        when(stockService.getStocks(any())).thenReturn(responseDtoResponseDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(ApiEndPoint.STOCK_BASE_URL + "/books?page=0&size=10")
                .contentType(MediaType.APPLICATION_JSON);
        MockMvcBuilders.standaloneSetup(this.StockController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetStocksFailure() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(ApiEndPoint.STOCK_BASE_URL+"/books")
                .contentType(MediaType.APPLICATION_JSON);
        MockMvcBuilders.standaloneSetup(this.StockController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }

    @Test
    void testGetStocksURLNotFound() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(ApiEndPoint.STOCK_BASE_URL + "/test")
                .contentType(MediaType.APPLICATION_JSON);
        MockMvcBuilders.standaloneSetup(this.StockController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
    @Test
    void testGetStockByBookSuccess() throws Exception {
        StockDto stockDto = new StockDto();
        stockDto.setStockId(1L);
        stockDto.setBookId(2L);
        stockDto.setQuantity(100);

        ResponseDto<StockDto> responseDtoResponseDto = ResponseDto.buildSuccess(stockDto);
        when(stockService.getStockByBookId(any())).thenReturn(responseDtoResponseDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(ApiEndPoint.STOCK_BASE_URL + "/book/1")
                .contentType(MediaType.APPLICATION_JSON);
        MockMvcBuilders.standaloneSetup(this.StockController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }



}
