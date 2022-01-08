package com.getir.bookstore.validator;

import com.getir.bookstore.domain.Customer;
import com.getir.bookstore.dto.request.CustomerRegisterDto;
import com.getir.bookstore.repository.CustomerRepository;
import com.getir.bookstore.util.BeanUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class CustomerValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        CustomerRegisterDto customer = (CustomerRegisterDto) target;
        if (StringUtils.isNotEmpty(customer.getUserName())) {
            CustomerRepository customerRepository = BeanUtil.getBean(CustomerRepository.class);
            Optional<Customer> optionalCustomer = customerRepository.findByUserName(customer.getUserName());
            if (optionalCustomer.isPresent()) {
                errors.rejectValue("userName", "Unique.customerRegisterDto.userName");
            }
        }
    }
}
