package com.vitamincode.vitamincode_be.service;

import com.vitamincode.vitamincode_be.dto.request.ClassDtoRequest;
import com.vitamincode.vitamincode_be.dto.response.ClassDtoResponse;

import java.util.List;

public interface ClassService {
    List<ClassDtoResponse> findAllClass();

    List<ClassDtoResponse> findClassById(Integer classId);

    List<ClassDtoResponse> findClass(ClassDtoRequest classDtoRequest);

    Integer deleteClassById(Integer classId);

    Integer saveClass(ClassDtoRequest classDtoRequest);
}
