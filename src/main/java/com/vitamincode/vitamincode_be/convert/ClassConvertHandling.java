package com.vitamincode.vitamincode_be.convert;

import com.vitamincode.vitamincode_be.dto.response.ClassDtoResponse;
import com.vitamincode.vitamincode_be.entity.Class;

import java.util.List;
import java.util.stream.Collectors;

public class ClassConvertHandling {
    public static ClassDtoResponse classEntityConvertToClassDtoResponse(Class classEntity) {
        return ClassDtoResponse.builder()
                .classId(classEntity.getClassId())
                .className(classEntity.getClassName())
                .classDescription(classEntity.getClassDescription())
                .build();
    }

    public static List<ClassDtoResponse> listClassEntityConvertToListClassDtoResponse(List<Class> classEntity) {
        return classEntity
                .stream()
                .map(ClassConvertHandling::classEntityConvertToClassDtoResponse)
                .collect(Collectors.toList());
    }
}
