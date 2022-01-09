package com.getir.bookstore.controller;


import com.getir.bookstore.constant.ApiEndPoint;
import com.getir.bookstore.dto.request.AuthenticationRequestDto;
import com.getir.bookstore.dto.response.ResponseDto;
import com.getir.bookstore.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping(ApiEndPoint.OAUTH_BASE_URL)
public class AuthenticationController extends BaseController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/token")
    public ResponseEntity<ResponseDto> signin(@RequestBody @Valid AuthenticationRequestDto requestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(getErrors(bindingResult));
        } else {
            return ResponseEntity.ok(ResponseDto.buildSuccess(authenticationService.getToken(requestDto)));
        }
    }
}
