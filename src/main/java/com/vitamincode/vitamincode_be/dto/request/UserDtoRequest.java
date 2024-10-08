package com.vitamincode.vitamincode_be.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDtoRequest {
    private String userName;
    private String password;
    private String email;
    private Boolean enabled;
    private Integer roleId;
}
