package com.techzen.techlearn.controller;

import com.techzen.techlearn.dto.request.ChapterRequestDTO;
import com.techzen.techlearn.dto.request.OrderDTO;
import com.techzen.techlearn.service.ChapterService;
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
@RequestMapping("/api/v1/chapters")
public class ChapterController {

    ChapterService chapterService;

    @GetMapping
    public ResponseEntity<?> getAllChapters(@RequestParam(required = false, defaultValue = "1") int page,
                                            @RequestParam(required = false, defaultValue = "10") int pageSize,
                                            @RequestParam Long idCourse){
        return JsonResponse.ok(chapterService.getAllChapters(page, pageSize, idCourse));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getChapterById(@PathVariable Long id) {
        return JsonResponse.ok(chapterService.getChapterById(id));
    }

    @PostMapping
    public ResponseEntity<?> addChapter(@RequestBody @Valid ChapterRequestDTO request) {
        return JsonResponse.ok(chapterService.addChapter(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateChapter(@PathVariable Long id, @RequestBody ChapterRequestDTO request) {
        return JsonResponse.ok(chapterService.updateChapter(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteChapter(@PathVariable Long id) {
        chapterService.deleteChapter(id);
        return JsonResponse.deleted();
    }

    @PatchMapping("/update-order")
    public ResponseEntity<?> updateLessonOrder(@RequestBody List<OrderDTO> orderDTOS) {
        chapterService.updateOrder(orderDTOS);
        return ResponseEntity.ok().build();
    }
}
