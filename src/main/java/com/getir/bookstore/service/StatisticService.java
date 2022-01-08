package com.getir.bookstore.service;

import com.getir.bookstore.dto.response.ResponseDto;
import com.getir.bookstore.dto.response.StatisticDto;

import java.util.List;

public interface StatisticService {
    ResponseDto<List<StatisticDto>> getMonthlyStatistic(Long customerId);

    ResponseDto<List<StatisticDto>> getMonthlyStatistic();
}
