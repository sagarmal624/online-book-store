package com.getir.bookstore.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto extends BaseDto {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String mobileNumber;
    private String address;
}
