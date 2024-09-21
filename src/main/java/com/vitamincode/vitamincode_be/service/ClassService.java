package com.vitamincode.vitamincode_be.service;

import com.vitamincode.vitamincode_be.entity.Class;

import java.util.List;

public interface ClassService {
    List<Class> findAllClass();

    Class findClassById(Integer classId);

    Integer insertClass(Class classEntity);

    Integer updateClass(Class classEntity);
    
    Integer deleteClassById(Integer classId);
}
