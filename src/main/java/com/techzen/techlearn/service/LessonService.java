package com.techzen.techlearn.service;

import com.techzen.techlearn.dto.request.OrderDTO;
import com.techzen.techlearn.dto.request.LessonRequestDTO;
import com.techzen.techlearn.dto.response.LessonResponseDTO;
import com.techzen.techlearn.dto.response.PageResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LessonService {
    PageResponse<?> getAllLesson(int page, int pageSize, Long idChapter);

    LessonResponseDTO getLessonById(Long id);

    LessonResponseDTO addLesson(LessonRequestDTO request);

    LessonResponseDTO updateLesson(Long id, LessonRequestDTO request);

    void deleteLesson(Long id);

    void updateOrder(List<OrderDTO> lessonOrderList);
}
