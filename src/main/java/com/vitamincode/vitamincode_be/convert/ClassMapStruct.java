package com.vitamincode.vitamincode_be.convert;

import com.vitamincode.vitamincode_be.dto.request.ClassDtoRequest;
import com.vitamincode.vitamincode_be.dto.response.ClassDtoResponse;
import com.vitamincode.vitamincode_be.entity.Class;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClassMapStruct {
    ClassDtoResponse toClassDtoResponse(Class classEntity);
    Class toClass(ClassDtoRequest classDtoRequest);
}
