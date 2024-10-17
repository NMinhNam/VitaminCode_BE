package com.vitamincode.vitamincode_be.controller;

import com.vitamincode.vitamincode_be.dto.response.ApiResponse;
import com.vitamincode.vitamincode_be.dto.response.UserDtoResponse;
import com.vitamincode.vitamincode_be.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    ApiResponse<List<UserDtoResponse>> getAllUser() {
        return ApiResponse.<List<UserDtoResponse>>builder()
                .status(HttpStatus.OK.value())
                .success(true)
                .data(userService.getAllUser())
                .build();
    }

    @GetMapping("/search")
    ApiResponse<UserDtoResponse> getUserById(@RequestParam("username") String userName) {
        return ApiResponse.<UserDtoResponse>builder()
                .status(HttpStatus.OK.value())
                .success(true)
                .data(userService.getByUserName(userName))
                .build();
    }

    @GetMapping("/me")
    ApiResponse<UserDtoResponse> getUserInfo() {
        return ApiResponse.<UserDtoResponse>builder()
                .status(HttpStatus.OK.value())
                .success(true)
                .data(userService.getUserInfo())
                .build();
    }
}
