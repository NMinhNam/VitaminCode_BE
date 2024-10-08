package com.vitamincode.vitamincode_be.controller;

import com.vitamincode.vitamincode_be.dto.response.ApiResponse;
import com.vitamincode.vitamincode_be.dto.response.UserDtoResponse;
import com.vitamincode.vitamincode_be.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/getAllUser")
    ApiResponse<List<UserDtoResponse>> getAllUser() {
        return ApiResponse.<List<UserDtoResponse>>builder()
                .status(HttpStatus.OK.value())
                .success(true)
                .data(userService.getAllUser())
                .build();
    }
}
