package com.techzen.techlearn.controller;

import com.techzen.techlearn.dto.request.OrderDTO;
import com.techzen.techlearn.dto.request.LessonRequestDTO;
import com.techzen.techlearn.service.LessonService;
import com.techzen.techlearn.util.JsonResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/lessons")
public class LessonController {

    LessonService lessonService;

    @GetMapping
    public ResponseEntity<?> getAllLesson(@RequestParam(required = false, defaultValue = "1") int page,
                                          @RequestParam(required = false, defaultValue = "10") int pageSize,
                                          @RequestParam Long idChapter) {
        return JsonResponse.ok(lessonService.getAllLesson(page, pageSize, idChapter));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLessonById(@PathVariable Long id) {
        return JsonResponse.ok(lessonService.getLessonById(id));
    }

    @PostMapping
    public ResponseEntity<?> addLesson(@RequestBody @Valid LessonRequestDTO request) {
        return JsonResponse.ok(lessonService.addLesson(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLesson(@PathVariable Long id, @RequestBody @Valid LessonRequestDTO request) {
        return JsonResponse.ok(lessonService.updateLesson(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLesson(@PathVariable Long id) {
        lessonService.deleteLesson(id);
        return JsonResponse.deleted();
    }

    @PatchMapping("/update-order")
    public ResponseEntity<?> updateLessonOrder(@RequestBody List<OrderDTO> lessonOrderList) {
        lessonService.updateOrder(lessonOrderList);
        return ResponseEntity.ok().build();
    }
}
