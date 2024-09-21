package com.vitamincode.vitamincode_be.controller;

import com.vitamincode.vitamincode_be.entity.Class;
import com.vitamincode.vitamincode_be.service.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getClassById/{id}")
    public ResponseEntity<?> getClassById(@PathVariable("id") Integer classId) {
        Map<String, Object> resultMapAPI = new LinkedHashMap<>();
        try {
            resultMapAPI.put("status", 200);
            resultMapAPI.put("success", true);
            resultMapAPI.put("data", classService.findClassById(classId));
        } catch (Exception e) {
            resultMapAPI.put("status", 500);
            resultMapAPI.put("success", false);
            resultMapAPI.put("message", e.getMessage());
        }
        return ResponseEntity.ok(resultMapAPI);
    }

    @GetMapping("/getClass")
    public ResponseEntity<?> getClass(@RequestParam("id") Integer classId) {
        Map<String, Object> resultMapAPI = new LinkedHashMap<>();
        try {
            resultMapAPI.put("status", 200);
            resultMapAPI.put("success", true);
            resultMapAPI.put("data", classService.findClassById(classId));
        } catch (Exception e) {
            resultMapAPI.put("status", 500);
            resultMapAPI.put("success", false);
            resultMapAPI.put("message", e.getMessage());
        }
        return ResponseEntity.ok(resultMapAPI);
    }

    @PostMapping("/createClass")
    public ResponseEntity<?> createClass(@RequestBody Class classEntity) {
        Map<String, Object> resultMapAPI = new LinkedHashMap<>();
        try {
            resultMapAPI.put("status", 200);
            resultMapAPI.put("success", true);
            resultMapAPI.put("data", classService.insertClass(classEntity));
        } catch (Exception e) {
            resultMapAPI.put("status", 500);
            resultMapAPI.put("success", false);
            resultMapAPI.put("message", e.getMessage());
        }
        return ResponseEntity.ok(resultMapAPI);
    }

    @PutMapping("/updateClass")
    public ResponseEntity<?> updateClass(@RequestBody Class classEntity) {
        Map<String, Object> resultMapAPI = new LinkedHashMap<>();
        try {
            resultMapAPI.put("status", 200);
            resultMapAPI.put("success", true);
            resultMapAPI.put("data", classService.updateClass(classEntity));
        } catch (Exception e) {
            resultMapAPI.put("status", 500);
            resultMapAPI.put("success", false);
            resultMapAPI.put("message", e.getMessage());
        }
        return ResponseEntity.ok(resultMapAPI);
    }

    @DeleteMapping("/deleteClass/{id}")
    public ResponseEntity<?> deleteClass(@PathVariable("id") Integer classId) {
        Map<String, Object> resultMapAPI = new LinkedHashMap<>();
        try {
            resultMapAPI.put("status", 200);
            resultMapAPI.put("success", true);
            resultMapAPI.put("data", classService.deleteClassById(classId));
        } catch (Exception e) {
            resultMapAPI.put("status", 500);
            resultMapAPI.put("success", false);
            resultMapAPI.put("message", e.getMessage());
        }
        return ResponseEntity.ok(resultMapAPI);
    }
}