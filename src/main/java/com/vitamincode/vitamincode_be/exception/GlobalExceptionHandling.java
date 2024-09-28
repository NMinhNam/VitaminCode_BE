package com.vitamincode.vitamincode_be.exception;

import com.vitamincode.vitamincode_be.dto.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandling {
    private void logError(Exception e, HttpServletRequest request) {
        log.error("Failed to call API " + request.getRequestURI() + " : " + e);
    }

    @ExceptionHandler(value = BadSqlGrammarException.class)
    ResponseEntity<ApiResponse> handleBadSqlGrammarException(BadSqlGrammarException e, HttpServletRequest request) {
        logError(e, request);
        return ResponseEntity.badRequest().body(
                ApiResponse.builder()
                .status(ErrorCode.BAD_SQL.getStatus())
                .message(ErrorCode.BAD_SQL.getMessage())
                .build()
        );
    }

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> handleException(Exception e, HttpServletRequest request) {
        logError(e, request);
        return ResponseEntity.badRequest().body(
                ApiResponse.builder()
                        .status(ErrorCode.UNCAUGHT_EXCEPTION.getStatus())
                        .message(ErrorCode.UNCAUGHT_EXCEPTION.getMessage())
                        .build()
        );
    }
}
