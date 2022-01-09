package com.getir.bookstore.service.impl;

import com.getir.bookstore.domain.Customer;
import com.getir.bookstore.domain.Statistic;
import com.getir.bookstore.dto.response.ResponseDto;
import com.getir.bookstore.dto.response.StatisticDto;
import com.getir.bookstore.repository.OrderRepository;
import com.getir.bookstore.service.AuthenticationService;
import com.getir.bookstore.service.StatisticService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StatisticServiceImpl implements StatisticService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public ResponseDto<List<StatisticDto>> getMonthlyStatistic(Long customerId) {
        return ResponseDto.buildSuccess(getStatisticDto(customerId));
    }

    public List<StatisticDto> getStatisticDto(Long customerId) {
        List<Statistic> statistics = orderRepository.getGroupByMonthlyReport(customerId);
        return statistics.stream().map(statistic ->
                StatisticDto.builder()
                        .month(Month.of(statistic.getMonth()).name())
                        .totalAmount(statistic.getTotalAmount())
                        .totalPurchaseBookCount(statistic.getTotalPurchaseBookCount())
                        .totalOrder(statistic.getTotalOrder())
                        .build()).collect(Collectors.toList());

    }

    @Override
    public ResponseDto<List<StatisticDto>> getMonthlyStatistic() {
        Customer customer = authenticationService.getLoginCustomer();
        return ResponseDto.buildSuccess(getStatisticDto(customer.getId()));
    }
}
