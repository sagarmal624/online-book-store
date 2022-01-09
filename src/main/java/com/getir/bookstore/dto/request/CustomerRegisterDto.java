package com.getir.bookstore.dto.request;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@Validated
public class CustomerRegisterDto {
    @NotEmpty
    private String firstName;
    private String lastName;
    @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
    @Pattern(regexp = "^\\d{10}$")
    private String mobileNumber;
}
