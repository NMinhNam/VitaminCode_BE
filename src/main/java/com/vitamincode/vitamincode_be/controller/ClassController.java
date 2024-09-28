package com.vitamincode.vitamincode_be.controller;

import com.vitamincode.vitamincode_be.dto.request.ClassDtoRequest;
import com.vitamincode.vitamincode_be.dto.response.ApiResponse;
import com.vitamincode.vitamincode_be.service.ClassService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/class")
@RequiredArgsConstructor
@Slf4j
public class ClassController {
    private final ClassService classService;

    @GetMapping("/getAllClass")
    public ApiResponse<?> getAllClass() {
        return ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .success(true)
                .data(classService.findAllClass())
                .build();
    }

    @GetMapping("/getClassById/{id}")
    public ApiResponse<?> getClassById(@PathVariable("id") Integer classId) {
        return ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .success(true)
                .data(classService.findClassById(classId))
                .build();
    }

    @GetMapping("/getClass")
    public ApiResponse<?> getClass(@RequestParam("id") Integer classId) {
        return ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .success(true)
                .data(classService.findClassById(classId))
                .build();
    }

    @PostMapping("/createClass")
    public ApiResponse<?> createClass(@RequestBody ClassDtoRequest classEntity) {
        return ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .success(true)
                .data(classService.insertClass(classEntity))
                .build();
    }

    @PutMapping("/updateClass")
    public ApiResponse<?> updateClass(@RequestBody ClassDtoRequest classEntity) {
        return ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .success(true)
                .data(classService.updateClass(classEntity))
                .build();
    }

    @DeleteMapping("/deleteClass/{id}")
    public ApiResponse<?> deleteClass(@PathVariable("id") Integer classId) {
        return ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .success(true)
                .data(classService.deleteClassById(classId))
                .build();
    }
}