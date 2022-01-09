package com.getir.bookstore.validator;

import com.getir.bookstore.domain.Customer;
import com.getir.bookstore.dto.request.CustomerRegisterDto;
import com.getir.bookstore.repository.CustomerRepository;
import com.getir.bookstore.util.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
@Slf4j
public class CustomerValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        CustomerRegisterDto customer = (CustomerRegisterDto) target;
        if (StringUtils.isNotEmpty(customer.getEmail())) {
            CustomerRepository customerRepository = BeanUtil.getBean(CustomerRepository.class);
            Optional<Customer> optionalCustomer = customerRepository.findByEmail(customer.getEmail());
            if (optionalCustomer.isPresent()) {
                errors.rejectValue("email", "Unique.customerRegisterDto.email");
            }
        }
    }
}
