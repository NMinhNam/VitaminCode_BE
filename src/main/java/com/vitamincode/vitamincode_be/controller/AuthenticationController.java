package com.vitamincode.vitamincode_be.controller;

import com.nimbusds.jose.JOSEException;
import com.vitamincode.vitamincode_be.dto.request.AuthenticationDtoRequest;
import com.vitamincode.vitamincode_be.dto.response.ApiResponse;
import com.vitamincode.vitamincode_be.dto.response.AuthenticationDtoResponse;
import com.vitamincode.vitamincode_be.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    ApiResponse<AuthenticationDtoResponse> login(@RequestBody AuthenticationDtoRequest request) throws JOSEException {
        return ApiResponse.<AuthenticationDtoResponse>builder()
                .status(HttpStatus.OK.value())
                .success(true)
                .data(authenticationService.authenticate(request))
                .build();
    }
}
