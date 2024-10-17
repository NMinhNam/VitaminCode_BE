package com.vitamincode.vitamincode_be.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogoutDtoRequest {
    private String accessToken;
}
