package com.vitamincode.vitamincode_be.service;

import com.vitamincode.vitamincode_be.dto.response.UserDtoResponse;

import java.util.List;

public interface UserService {
    List<UserDtoResponse> getAllUser();
    UserDtoResponse getUserInfo();
    UserDtoResponse getByUserName(String userName);
}
