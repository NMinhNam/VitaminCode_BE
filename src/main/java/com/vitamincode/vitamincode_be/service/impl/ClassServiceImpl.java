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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ClassServiceImpl implements ClassService {
    private final ClassMapper classMapper;
    private final ClassMapStruct classMapStruct;

    @Override
    public List<ClassDtoResponse> findAllClass() {
        var listEntityResponse = classMapper.selectAllClass();
        if(listEntityResponse.isEmpty()) throw new AppException(ErrorCode.LIST_CLASS_EMPTY);
        return classMapStruct.toClassDtoResponseList(listEntityResponse);
    }

    @Override
    public ClassDtoResponse findClassById(Integer classId) {
        var resultEntity = classMapper.selectClassById(classId);
        if (Objects.isNull(resultEntity)) throw new AppException(ErrorCode.CLASS_EMPTY);
        return classMapStruct.toClassDtoResponse(resultEntity);
    }

    @Override
    public Integer insertClass(ClassDtoRequest classDtoRequest) {
        var classEntity = classMapStruct.toClass(classDtoRequest);
        return classMapper.insertClass(classEntity);
    }

    @Override
    public Integer updateClass(ClassDtoRequest classDtoRequest) {
        var classEntity = classMapStruct.toClass(classDtoRequest);
        return classMapper.updateClass(classEntity);
    }

    @Override
    public Integer deleteClassById(Integer classId) {
        return classMapper.deleteClassById(classId);
    }
}
