package com.vitamincode.vitamincode_be.service;

import com.vitamincode.vitamincode_be.dto.request.ClassDtoRequest;
import com.vitamincode.vitamincode_be.dto.response.ClassDtoResponse;

import java.util.List;

public interface ClassService {
    List<ClassDtoResponse> findAllClass();

    ClassDtoResponse findClassById(Integer classId);

    Integer insertClass(ClassDtoRequest classEntity);

    Integer updateClass(ClassDtoRequest classEntity);
    
    Integer deleteClassById(Integer classId);
}
