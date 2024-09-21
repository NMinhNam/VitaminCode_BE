package com.vitamincode.vitamincode_be.service.impl;

import com.vitamincode.vitamincode_be.entity.Class;
import com.vitamincode.vitamincode_be.mapper.ClassMapper;
import com.vitamincode.vitamincode_be.service.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ClassServiceImpl implements ClassService {
    private final ClassMapper classMapper;

    @Override
    public List<Class> findAllClass() {
        if (!classMapper.selectAllClass().isEmpty()) {
            return classMapper.selectAllClass();
        }
        return null;
    }

    @Override
    public Class findClassById(Integer classId) {
        var resultEntity = classMapper.selectClassById(classId);
        if(Objects.nonNull(resultEntity)) {
            return resultEntity;
        }
        return null;
    }

    @Override
    public Integer insertClass(Class classEntity) {
        return classMapper.insertClass(classEntity);
    }

    @Override
    public Integer updateClass(Class classEntity) {
        return classMapper.updateClass(classEntity);
    }

    @Override
    public Integer deleteClassById(Integer classId) {
        return classMapper.deleteClassById(classId);
    }
}
