package com.techzen.techlearn.controller;

import com.techzen.techlearn.dto.request.TeacherRequestDTO;
import com.techzen.techlearn.service.TeacherService;
import com.techzen.techlearn.util.JsonResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/teachers")
public class TeacherController {
    TeacherService teacherService;
    @GetMapping
    public ResponseEntity<?> getAllTeacher(@RequestParam(required = false, defaultValue = "1") int page,
                                        @RequestParam(required = false, defaultValue = "10") int pageSize) {
        return JsonResponse.ok(teacherService.findAll(page, pageSize));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTeacherById(@PathVariable UUID id) {
        return JsonResponse.ok(teacherService.getTeacherById(id));
    }

    @PostMapping
    public ResponseEntity<?> addTeacher(@RequestBody @Valid TeacherRequestDTO request) {
        return JsonResponse.ok(teacherService.addTeacher(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTeacher(@PathVariable UUID id, @RequestBody @Valid TeacherRequestDTO request) {
        return JsonResponse.ok(teacherService.updateTeacher(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable UUID id) {
        teacherService.deleteTeacher(id);
        return JsonResponse.deleted();
    }
}
