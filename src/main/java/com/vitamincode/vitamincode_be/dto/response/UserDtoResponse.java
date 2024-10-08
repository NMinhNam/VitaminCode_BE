package com.vitamincode.vitamincode_be.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDtoResponse {
    private String userName;
    private String email;
    private Boolean enabled;
    private Integer roleId;
}
