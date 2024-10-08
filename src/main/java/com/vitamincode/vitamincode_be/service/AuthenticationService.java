package com.vitamincode.vitamincode_be.service;

import com.nimbusds.jose.JOSEException;
import com.vitamincode.vitamincode_be.dto.request.AuthenticationDtoRequest;
import com.vitamincode.vitamincode_be.dto.response.AuthenticationDtoResponse;

public interface AuthenticationService {
    AuthenticationDtoResponse authenticate(AuthenticationDtoRequest request) throws JOSEException;
}
