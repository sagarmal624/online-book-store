package com.getir.bookstore.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.getir.bookstore.constant.enums.BookStoreErrorCode;
import com.getir.bookstore.constant.enums.BookStoreSuccessCode;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto<T> {
    private Integer code;
    private String message;
    private Boolean success;
    private T data;
    private List<ErrorResponse> errors;

    public static <T> ResponseDto<T> buildSuccess(T data) {
        return ResponseDto.<T>builder().success(true).code(BookStoreSuccessCode.STORE_SUCCESS_CODE.getCode()).data(data).build();
    }

    public static <T> ResponseDto<T> buildSuccess() {
        return ResponseDto.<T>builder().success(true).code(BookStoreSuccessCode.STORE_SUCCESS_CODE.getCode()).message("Record is Saved successfully").build();
    }

    public static <T> ResponseDto<T> buildError() {
        return ResponseDto.<T>builder().success(false).code(BookStoreErrorCode.INTERNAL_SERVER_ERROR.getCode()).message("Something Went Wrong.Internal Server Error.Please try again").data(null).errors(null).build();
    }

    public static <T> ResponseDto<T> buildError(List<ErrorResponse> errors) {
        return ResponseDto.<T>builder().success(false).code(BookStoreErrorCode.FIELD_VALIDATION_ERROR.getCode()).message("Data Validation Errors Occurred").errors(errors).build();
    }
}
