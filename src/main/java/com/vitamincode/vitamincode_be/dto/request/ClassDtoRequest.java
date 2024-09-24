package com.vitamincode.vitamincode_be.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassDtoRequest {
    private Integer classId;
    private String className;
    private String classDescription;
}
