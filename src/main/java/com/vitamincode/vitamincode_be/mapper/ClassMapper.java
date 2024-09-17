package com.vitamincode.vitamincode_be.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClassMapper {
    List<Class> selectAllClass();
}
