package com.vitamincode.vitamincode_be.service.impl;

import com.vitamincode.vitamincode_be.convert.ClassMapStruct;
import com.vitamincode.vitamincode_be.dto.request.ClassDtoRequest;
import com.vitamincode.vitamincode_be.dto.response.ClassDtoResponse;
import com.vitamincode.vitamincode_be.exception.AppException;
import com.vitamincode.vitamincode_be.exception.ErrorCode;
import com.vitamincode.vitamincode_be.mapper.ClassMapper;
import com.vitamincode.vitamincode_be.service.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassServiceImpl implements ClassService {
    private final ClassMapper classMapper;
    private final ClassMapStruct classMapStruct;

    @Override
    public List<ClassDtoResponse> findAllClass() {
        var listEntityResponse = classMapper.selectAllClass();
        if (listEntityResponse.isEmpty()) throw new AppException(ErrorCode.LIST_CLASS_EMPTY);
        return classMapStruct.toClassDtoResponseList(listEntityResponse);
    }

    @Override
    public List<ClassDtoResponse> findClassById(Integer classId) {
        var resultEntity = classMapper.selectClassById(classId);
        if (resultEntity.isEmpty()) throw new AppException(ErrorCode.CLASS_EMPTY);
        return classMapStruct.toClassDtoResponseList(resultEntity);
    }

    @Override
    public List<ClassDtoResponse> findClass(ClassDtoRequest classDtoRequest) {
        var classEntity = classMapStruct.toClass(classDtoRequest);
        return classMapStruct.toClassDtoResponseList(
                classMapper.selectClass(classEntity)
        );
    }

    @Override
    public Integer deleteClassById(Integer classId) {
        return classMapper.deleteClassById(classId);
    }

    @Override
    public Integer saveClass(ClassDtoRequest classDtoRequest) {
        Integer classId = classDtoRequest.getClassId();
        var classEntity = classMapStruct.toClass(classDtoRequest);
        if(classMapper.isClassExists(classId)) {
            return classMapper.updateClass(classEntity);
        }
        return classMapper.insertClass(classEntity);
    }
}
