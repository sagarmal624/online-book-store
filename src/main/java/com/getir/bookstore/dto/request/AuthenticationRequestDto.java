package com.getir.bookstore.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class AuthenticationRequestDto {
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
}
