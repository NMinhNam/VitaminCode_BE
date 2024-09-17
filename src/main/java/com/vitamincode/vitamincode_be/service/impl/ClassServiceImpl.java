package com.vitamincode.vitamincode_be.service.impl;

import com.vitamincode.vitamincode_be.entity.Class;
import com.vitamincode.vitamincode_be.mapper.ClassMapper;
import com.vitamincode.vitamincode_be.service.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
