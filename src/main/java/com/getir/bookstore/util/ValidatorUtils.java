package com.getir.bookstore.util;

import com.getir.bookstore.constant.enums.BookStoreErrorCode;
import com.getir.bookstore.dto.response.ErrorResponse;
import com.getir.bookstore.dto.response.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
@Slf4j
public class ValidatorUtils {
    private ValidatorUtils() {

    }

    public static ResponseDto<List<ErrorResponse>> getErrors(BindingResult bindingResult, MessageSource messageSource) {
        List<ErrorResponse> errorResponses = new ArrayList<>();
        for (Object object : bindingResult.getAllErrors()) {
            if (object instanceof FieldError) {
                FieldError fieldError = (FieldError) object;
                String message = messageSource.getMessage(fieldError, Locale.US);
                errorResponses.add(ErrorResponse.builder().fieldName(fieldError.getField()).message(message).build());
            }
        }
        ResponseDto<List<ErrorResponse>> responseDto = ResponseDto.buildError(errorResponses);
        responseDto.setCode(BookStoreErrorCode.UNIQUE_RECORD_VALIDATION_ERROR.getCode());
        return responseDto;
    }
}
