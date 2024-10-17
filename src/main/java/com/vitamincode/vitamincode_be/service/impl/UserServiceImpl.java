package com.vitamincode.vitamincode_be.service.impl;

import com.vitamincode.vitamincode_be.convert.UserMapStruct;
import com.vitamincode.vitamincode_be.dto.response.UserDtoResponse;
import com.vitamincode.vitamincode_be.entity.User;
import com.vitamincode.vitamincode_be.enums.ErrorCode;
import com.vitamincode.vitamincode_be.exception.AppException;
import com.vitamincode.vitamincode_be.repository.UserRepository;
import com.vitamincode.vitamincode_be.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapStruct userMapStruct;

    @Override
    public List<UserDtoResponse> getAllUser() {
        var listEntity = userRepository.findAll();
        return userMapStruct.toUserDtoResponseList(listEntity);
    }

    @Override
    public UserDtoResponse getUserInfo() {
        var securityContext = SecurityContextHolder.getContext();
        String username = securityContext.getAuthentication().getName();
        User entity = userRepository.findByUserName(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXIST));
        return userMapStruct.toUserDtoResponse(entity);
    }

    @Override
    @PreAuthorize("#userName == authentication.name")
    public UserDtoResponse getByUserName(String userName) {
        var entity = userRepository.findByUserName(userName)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXIST));
        return userMapStruct.toUserDtoResponse(entity);
    }
}
