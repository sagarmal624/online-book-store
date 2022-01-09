package com.getir.bookstore.controller;

import com.getir.bookstore.constant.ApiEndPoint;
import com.getir.bookstore.dto.request.OrderFilterRequestDto;
import com.getir.bookstore.dto.request.OrderRequestDto;
import com.getir.bookstore.dto.request.PageRequestDto;
import com.getir.bookstore.dto.response.OrderDto;
import com.getir.bookstore.dto.response.ResponseDto;
import com.getir.bookstore.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping(ApiEndPoint.ORDER_BASE_URL)
public class OrderController extends BaseController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<ResponseDto> createOrder(@Valid @RequestBody OrderRequestDto orderRequestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(getErrors(bindingResult));
        }
        return ResponseEntity.ok(orderService.createOrder(orderRequestDto.getOrders()));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<ResponseDto<OrderDto>> findOrderById(@PathVariable("orderId") Long orderId) {
        return ResponseEntity.ok(orderService.getOrdersById(orderId));
    }

    @GetMapping
    public ResponseEntity<ResponseDto> getAllOrder(@Valid PageRequestDto pageRequestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(getErrors(bindingResult));
        }
        return ResponseEntity.ok(orderService.getOrdersByCustomer(pageRequestDto));
    }

    @GetMapping("/filters")
    public ResponseEntity<ResponseDto> filterByDateRange(@Valid OrderFilterRequestDto orderFilterRequestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(getErrors(bindingResult));
        }
        return ResponseEntity.ok(orderService.filterOrdersByDateRange(orderFilterRequestDto));
    }
}
