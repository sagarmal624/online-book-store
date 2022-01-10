package com.getir.bookstore.dto.response;

import lombok.Data;

@Data
public class CustomerDto extends BaseDto {
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
}
