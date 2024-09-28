package com.vitamincode.vitamincode_be.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    BAD_SQL(500, "Bad SQL! Vui lòng check lại câu lệnh sql"),
    UNCAUGHT_EXCEPTION(500, "Uncaught Exception! Chưa bắt được ngoại lệ"),
    LIST_CLASS_EMPTY(1000, "List Class is empty"),
    CLASS_EMPTY(1000, "Class is empty")
    ;

    private final Integer status;
    private final String message;
}
