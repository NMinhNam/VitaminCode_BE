package com.vitamincode.vitamincode_be.controller;

import com.vitamincode.vitamincode_be.mapper.ClassMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/class")
@RequiredArgsConstructor
public class ClassController {
    private final ClassMapper classMapper;

    @GetMapping("/getAllClass")
    public ResponseEntity<?> getAllClass() {
        return ResponseEntity.ok(classMapper.selectAllClass());
    }
}
