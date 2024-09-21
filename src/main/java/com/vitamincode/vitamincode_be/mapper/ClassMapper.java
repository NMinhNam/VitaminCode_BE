package com.vitamincode.vitamincode_be.mapper;

import com.vitamincode.vitamincode_be.entity.Class;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClassMapper {
    List<Class> selectAllClass();

    Class selectClassById(@Param("classId") Integer classId);

    int insertClass(@Param("classEntity") Class classEntity);

    int updateClass(@Param("classEntity") Class classEntity);

    int deleteClassById(@Param("classId") Integer classId);
}
