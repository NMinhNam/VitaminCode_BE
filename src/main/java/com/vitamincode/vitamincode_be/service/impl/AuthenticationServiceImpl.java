package com.vitamincode.vitamincode_be.service.impl;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.vitamincode.vitamincode_be.config.CustomJwtDecoder;
import com.vitamincode.vitamincode_be.dto.request.AuthenticationDtoRequest;
import com.vitamincode.vitamincode_be.dto.request.IntrospectDtoRequest;
import com.vitamincode.vitamincode_be.dto.request.LogoutDtoRequest;
import com.vitamincode.vitamincode_be.dto.response.AuthenticationDtoResponse;
import com.vitamincode.vitamincode_be.dto.response.IntrospectDtoResponse;
import com.vitamincode.vitamincode_be.entity.InvalidToken;
import com.vitamincode.vitamincode_be.entity.User;
import com.vitamincode.vitamincode_be.exception.AppException;
import com.vitamincode.vitamincode_be.enums.ErrorCode;
import com.vitamincode.vitamincode_be.repository.InvalidTokenRepository;
import com.vitamincode.vitamincode_be.repository.UserRepository;
import com.vitamincode.vitamincode_be.service.AuthenticationService;
import com.vitamincode.vitamincode_be.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder jwtDecoder;
    private final InvalidTokenRepository invalidTokenRepository;

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

    @Override
    public Void logout(LogoutDtoRequest request) throws ParseException, JOSEException {
        String accessToken = request.getAccessToken();
        var signedJWT = verifyJWT(accessToken);
        String jwtId = signedJWT.getJWTClaimsSet().getJWTID();
        Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        var exp = DateUtils.convertDateToTimestamp(expirationTime);

        InvalidToken invalidToken= InvalidToken.builder()
                .invalidTokenId(jwtId)
                .expTime(exp)
                .build();
        invalidTokenRepository.save(invalidToken);
        return null;
    }

    private SignedJWT verifyJWT(String accessToken) throws ParseException, JOSEException {
        SignedJWT signedJWT = SignedJWT.parse(accessToken);
        JWSVerifier verifier = new MACVerifier(SECRET_KEY.getBytes());

        Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        boolean isExpired = expirationTime.after(new Date());
        boolean isVerified = signedJWT.verify(verifier);

        String jwtId = signedJWT.getJWTClaimsSet().getJWTID();
        boolean isInvalidLogout = invalidTokenRepository
                .existsInvalidTokenByInvalidTokenId(jwtId);

        if (!(isExpired && isVerified && !isInvalidLogout)) {
            throw new AppException(ErrorCode.INVALID_TOKEN);
        }

        return signedJWT;
    }

    private String generateAccessToken(User user) throws JOSEException {
        String roleName = user.getRole().getRoleName();
        String userName = user.getUserName();

        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(userName)
                .jwtID(generateUUID())
                .issuer("minh_nam.com")
                .claim("scope", roleName)
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

    private String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
