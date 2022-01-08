package com.getir.bookstore.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponseDto {
    private String userName;
    private String token;
    @Builder.Default
    private String type = "Bearer";
    private long expired;
}
