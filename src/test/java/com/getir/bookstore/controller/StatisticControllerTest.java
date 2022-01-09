package com.getir.bookstore.controller;


import com.getir.bookstore.constant.ApiEndPoint;
import com.getir.bookstore.dto.response.ResponseDto;
import com.getir.bookstore.dto.response.StatisticDto;
import com.getir.bookstore.service.StatisticService;
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

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {StatisticController.class})
@ExtendWith(SpringExtension.class)
public class StatisticControllerTest {
    @MockBean
    private StatisticService statisticService;

    @Autowired
    private StatisticController statisticController;

    @Test
    void testGetMonthlyStatisticByCustomerId() throws Exception {
        ResponseDto<List<StatisticDto>> responseDto = ResponseDto.buildSuccess();
        when(statisticService.getMonthlyStatistic(any())).thenReturn(responseDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(ApiEndPoint.STATISTICS_BASE_URL + "/customer/1/report/monthly")
                .contentType(MediaType.APPLICATION_JSON);
        MockMvcBuilders.standaloneSetup(this.statisticController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }

    @Test
    void testGetMonthlyStatisticWithoutCustomerId() throws Exception {
        ResponseDto<List<StatisticDto>> responseDto = ResponseDto.buildSuccess();
        when(statisticService.getMonthlyStatistic()).thenReturn(responseDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(ApiEndPoint.STATISTICS_BASE_URL + "/customer/report/monthly")
                .contentType(MediaType.APPLICATION_JSON);
        MockMvcBuilders.standaloneSetup(this.statisticController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }

}
