package com.vitamincode.vitamincode_be.service.impl;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.vitamincode.vitamincode_be.config.CustomJwtDecoder;
import com.vitamincode.vitamincode_be.dto.request.AuthenticationDtoRequest;
import com.vitamincode.vitamincode_be.dto.request.IntrospectDtoRequest;
import com.vitamincode.vitamincode_be.dto.response.AuthenticationDtoResponse;
import com.vitamincode.vitamincode_be.dto.response.IntrospectDtoResponse;
import com.vitamincode.vitamincode_be.entity.User;
import com.vitamincode.vitamincode_be.exception.AppException;
import com.vitamincode.vitamincode_be.enums.ErrorCode;
import com.vitamincode.vitamincode_be.repository.UserRepository;
import com.vitamincode.vitamincode_be.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder jwtDecoder;

    @Value("${jwt.secret-key}")
    protected String SECRET_KEY;

    @Override
    public AuthenticationDtoResponse authenticate(AuthenticationDtoRequest request) throws JOSEException {
        String username = request.getUsername();

        var user = userRepository.findByUserName(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_EMPTY));

        boolean authenticated = jwtDecoder.matches(request.getPassword()
                , user.getPassword());
        if (!authenticated) throw new AppException(ErrorCode.PASSWORD_INCORRECT);

        return AuthenticationDtoResponse.builder()
                .accessToken(generateAccessToken(user))
                .build();
    }

    @Override
    public IntrospectDtoResponse introspect(IntrospectDtoRequest request) throws ParseException, JOSEException {
        String accessToken = request.getAccessToken();
        verifyJWT(accessToken);
        return IntrospectDtoResponse.builder()
                .isValid(true)
                .build();
    }

    private SignedJWT verifyJWT(String accessToken) throws ParseException, JOSEException {
        SignedJWT signedJWT = SignedJWT.parse(accessToken);
        JWSVerifier verifier = new MACVerifier(SECRET_KEY.getBytes());

        Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        boolean isExpired = expirationTime.after(new Date());
        boolean isVerified = signedJWT.verify(verifier);

        if (!(isExpired && isVerified)) {
            throw new AppException(ErrorCode.INVALID_TOKEN);
        }

        return signedJWT;
    }

    private String generateAccessToken(User user) throws JOSEException {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUserName())
                .issuer("minh_nam.com")
                .issueTime(new Date())
                .expirationTime(Date
                        .from(Instant.now()
                        .plus(1, ChronoUnit.HOURS)))
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        jwsObject.sign(new MACSigner(SECRET_KEY.getBytes()));
        return jwsObject.serialize();
    }
}
