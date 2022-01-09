package com.getir.bookstore.controller;

import com.getir.bookstore.constant.ApiEndPoint;
import com.getir.bookstore.dto.request.CustomerRegisterDto;
import com.getir.bookstore.dto.request.PageRequestDto;
import com.getir.bookstore.dto.response.ResponseDto;
import com.getir.bookstore.service.CustomerService;
import com.getir.bookstore.service.OrderService;
import com.getir.bookstore.validator.CustomerValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(ApiEndPoint.CUSTOMER_BASE_URL)
@Slf4j
public class CustomerController extends BaseController {
    @Autowired
    private CustomerValidator customerValidator;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<ResponseDto> registerCustomer(@Valid @RequestBody CustomerRegisterDto request, BindingResult bindingResult) {
        customerValidator.validate(request, bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(getErrors(bindingResult));
        }
        return ResponseEntity.ok(customerService.registerCustomer(request));
    }

    @GetMapping
    public ResponseEntity<ResponseDto> getCustomers(@Valid PageRequestDto pageRequestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(getErrors(bindingResult));
        }
        return ResponseEntity.ok(customerService.getCustomers(pageRequestDto));
    }

    @GetMapping("/{customerId}/orders")
    public ResponseEntity<ResponseDto> getCustomerOrders(@PathVariable("customerId") Long customerId, @Valid PageRequestDto pageRequestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(getErrors(bindingResult));
        }
        return ResponseEntity.ok(orderService.getOrdersByCustomer(customerId, pageRequestDto));
    }
}
