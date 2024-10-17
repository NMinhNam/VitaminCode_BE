package com.vitamincode.vitamincode_be.dto.response;

import com.vitamincode.vitamincode_be.entity.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDtoResponse {
    private String userName;
    private String email;
    private Boolean enabled;
    private Role role;
}
