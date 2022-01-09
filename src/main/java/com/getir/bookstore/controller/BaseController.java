package com.getir.bookstore.controller;

import com.getir.bookstore.dto.response.ErrorResponse;
import com.getir.bookstore.dto.response.ResponseDto;
import com.getir.bookstore.util.ValidatorUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import java.util.List;

@Controller
@Slf4j
public class BaseController {

    @Autowired
    private MessageSource messageSource;

    public ResponseDto<List<ErrorResponse>> getErrors(BindingResult bindingResult) {
        return ValidatorUtils.getErrors(bindingResult, messageSource);
    }
}
