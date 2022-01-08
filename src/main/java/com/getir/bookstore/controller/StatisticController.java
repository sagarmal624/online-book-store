package com.getir.bookstore.controller;

import com.getir.bookstore.constant.ApiEndPoint;
import com.getir.bookstore.dto.request.PageRequestDto;
import com.getir.bookstore.dto.response.ResponseDto;
import com.getir.bookstore.dto.response.StatisticDto;
import com.getir.bookstore.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(ApiEndPoint.STATISTICS_BASE_URL)
public class StatisticController extends BaseController {
    @Autowired
    private StatisticService statisticService;

    @GetMapping("/customer/{customerId}/monthly")
    public ResponseEntity<ResponseDto<List<StatisticDto>>> getMonthlyStatistic(@PathVariable("customerId") Long customerId) {
        return ResponseEntity.ok(statisticService.getMonthlyStatistic(customerId));
    }

    @GetMapping("/customer/monthly")
    public ResponseEntity<ResponseDto<List<StatisticDto>>> getMonthlyStatistic() {
        return ResponseEntity.ok(statisticService.getMonthlyStatistic());
    }
}
