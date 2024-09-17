package com.vitamincode.vitamincode_be.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {
    private Integer studentId;
    private String studentName;
    private String studentEmail;
    private Integer classId;
}
