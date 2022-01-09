package com.getir.bookstore.service.impl;

import com.getir.bookstore.constant.enums.BookStoreErrorCode;
import com.getir.bookstore.constant.enums.RoleType;
import com.getir.bookstore.domain.Customer;
import com.getir.bookstore.dto.request.CustomerRegisterDto;
import com.getir.bookstore.dto.request.PageRequestDto;
import com.getir.bookstore.dto.response.CustomerDto;
import com.getir.bookstore.dto.response.PageResponseDto;
import com.getir.bookstore.dto.response.ResponseDto;
import com.getir.bookstore.exception.RecordNotFoundException;
import com.getir.bookstore.repository.CustomerRepository;
import com.getir.bookstore.service.CustomerService;
import com.getir.bookstore.util.BookStoreUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j

public class CustomerServiceImpl implements CustomerService, UserDetailsService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseDto<Boolean> registerCustomer(CustomerRegisterDto request) {
        Customer customer = customerRepository.save(convertDtoToEntity(request));
        return Objects.nonNull(customer.getId()) ? ResponseDto.buildSuccess() : ResponseDto.buildError();
    }

    @Override
    public ResponseDto<PageResponseDto<CustomerDto>> getCustomers(PageRequestDto pageRequestDto) {
        return ResponseDto.buildSuccess(getCustomersFromDb(pageRequestDto));
    }


    private PageResponseDto<CustomerDto> getCustomersFromDb(PageRequestDto pageRequestDto) {
        Pageable pageable = PageRequest.of(pageRequestDto.getPage(), pageRequestDto.getSize());
        Page<Customer> page =  customerRepository.findAll(pageable);
        List<Customer> customers = page.getContent();
        return PageResponseDto.<CustomerDto>builder().records(getCustomerDtoList(customers)).page(BookStoreUtils.getPageDto(page)).build();
    }

    private List<CustomerDto> getCustomerDtoList(List<Customer> customers) {
        return customers.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    private CustomerDto convertEntityToDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        BeanUtils.copyProperties(customer, customerDto);
        return customerDto;
    }

    private Customer convertDtoToEntity(CustomerRegisterDto customerRegisterDto) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerRegisterDto, customer);
        customer.setPassword(passwordEncoder.encode(customerRegisterDto.getPassword()));
        customer.setRoles(new HashSet<>(Arrays.asList(RoleType.CUSTOMER.name())));
        return customer;
    }

    @Override
    public Customer loadUserByUsername(String username) throws UsernameNotFoundException {
        return customerRepository.findByEmail(username).orElseThrow(() -> new RecordNotFoundException(BookStoreErrorCode.CUSTOMER_NOT_FOUND.getMessage(), "userName"));
    }
}
