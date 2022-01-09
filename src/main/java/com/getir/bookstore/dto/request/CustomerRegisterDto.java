package com.getir.bookstore.dto.request;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

@Data
@Validated
public class CustomerRegisterDto {
    @NotEmpty
    private String firstName;
    private String lastName;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    private String mobileNumber;
    private String address;
}
