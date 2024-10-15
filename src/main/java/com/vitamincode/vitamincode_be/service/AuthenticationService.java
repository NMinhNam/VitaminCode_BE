package com.vitamincode.vitamincode_be.service;

import com.nimbusds.jose.JOSEException;
import com.vitamincode.vitamincode_be.dto.request.AuthenticationDtoRequest;
import com.vitamincode.vitamincode_be.dto.request.IntrospectDtoRequest;
import com.vitamincode.vitamincode_be.dto.response.AuthenticationDtoResponse;
import com.vitamincode.vitamincode_be.dto.response.IntrospectDtoResponse;

import java.text.ParseException;

public interface AuthenticationService {
    AuthenticationDtoResponse authenticate(AuthenticationDtoRequest request) throws JOSEException;
    IntrospectDtoResponse introspect(IntrospectDtoRequest request) throws JOSEException, ParseException;
}
