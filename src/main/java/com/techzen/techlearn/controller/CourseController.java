package com.techzen.techlearn.controller;

import com.techzen.techlearn.dto.request.CourseRequestDTO;
import com.techzen.techlearn.service.CourseService;
import com.techzen.techlearn.util.JsonResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/courses")
public class CourseController {

    CourseService courseService;

    @GetMapping
    public ResponseEntity<?> getAllCourse(@RequestParam(required = false, defaultValue = "1") int page,
                                          @RequestParam(required = false, defaultValue = "10") int pageSize) {
        return JsonResponse.ok(courseService.getAllCourse(page, pageSize));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable Long id) {
        return JsonResponse.ok(courseService.getCourseById(id));
    }

    @PostMapping
    public ResponseEntity<?> addCourse(@RequestBody @Valid CourseRequestDTO request) {
        return JsonResponse.ok(courseService.addCourse(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody @Valid CourseRequestDTO request) {
        return JsonResponse.ok(courseService.updateCourse(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return JsonResponse.deleted();
    }
}
