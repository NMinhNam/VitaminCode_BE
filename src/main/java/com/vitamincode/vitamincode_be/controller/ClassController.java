package com.vitamincode.vitamincode_be.controller;

import com.vitamincode.vitamincode_be.mapper.ClassMapper;
import com.vitamincode.vitamincode_be.service.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/class")
@RequiredArgsConstructor
public class ClassController {
    private final ClassService classService;

    @GetMapping("/getAllClass")
    public ResponseEntity<?> getAllClass() {
        Map<String, Object> resultMapAPI = new LinkedHashMap<>();
        try {
            resultMapAPI.put("status", 200);
            resultMapAPI.put("success", true);
            resultMapAPI.put("data", classService.findAllClass());
        } catch (Exception e) {
            resultMapAPI.put("status", 500);
            resultMapAPI.put("success", false);
            resultMapAPI.put("message", e.getMessage());
        }
        return ResponseEntity.ok(resultMapAPI);
    }
}
