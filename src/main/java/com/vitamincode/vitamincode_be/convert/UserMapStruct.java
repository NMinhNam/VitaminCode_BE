package com.vitamincode.vitamincode_be.convert;

import com.vitamincode.vitamincode_be.dto.response.UserDtoResponse;
import com.vitamincode.vitamincode_be.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapStruct {
    List<UserDtoResponse> toUserDtoResponseList(List<User> userList);
}
