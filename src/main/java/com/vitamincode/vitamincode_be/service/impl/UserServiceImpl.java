package com.vitamincode.vitamincode_be.service.impl;

import com.vitamincode.vitamincode_be.convert.UserMapStruct;
import com.vitamincode.vitamincode_be.dto.response.UserDtoResponse;
import com.vitamincode.vitamincode_be.repository.UserRepository;
import com.vitamincode.vitamincode_be.service.UserService;
import lombok.RequiredArgsConstructor;
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
}
