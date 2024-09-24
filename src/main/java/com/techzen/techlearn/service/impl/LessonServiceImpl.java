package com.techzen.techlearn.service.impl;

import com.techzen.techlearn.dto.request.OrderDTO;
import com.techzen.techlearn.dto.request.LessonRequestDTO;
import com.techzen.techlearn.dto.response.LessonResponseDTO;
import com.techzen.techlearn.dto.response.PageResponse;
import com.techzen.techlearn.entity.LessonEntity;
import com.techzen.techlearn.enums.ErrorCode;
import com.techzen.techlearn.exception.ApiException;
import com.techzen.techlearn.mapper.LessonMapper;
import com.techzen.techlearn.repository.ChapterRepository;
import com.techzen.techlearn.repository.LessonRepository;
import com.techzen.techlearn.service.LessonService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
public class LessonServiceImpl implements LessonService {

    LessonRepository lessonRepository;
    LessonMapper lessonMapper;
    ChapterRepository chapterRepository;

    @Override
    public PageResponse<?> getAllLesson(int page, int pageSize, Long idChapter) {
        Pageable pageable = PageRequest.of(page > 0 ? page - 1 : 0, pageSize,
                Sort.by("lessonOrder"));
        Page<LessonEntity> course = lessonRepository.findAllByChapterId(idChapter, pageable);
        List<LessonResponseDTO> list = course.map(lessonMapper::toLessonResponseDTO)
                .stream().collect(Collectors.toList());
        return PageResponse.builder()
                .page(page)
                .pageSize(pageSize)
                .totalPage(course.getTotalPages())
                .items(list)
                .build();
    }

    @Override
    public LessonResponseDTO getLessonById(Long id) {
        var lesson = lessonRepository.findById(id).orElseThrow(() ->
                new ApiException(ErrorCode.LESSON_NOT_EXISTED));
        return lessonMapper.toLessonResponseDTO(lesson);
    }

    @Override
    public LessonResponseDTO addLesson(LessonRequestDTO request) {
        var lesson = lessonMapper.toLessonEntity(request);
        var chapter = chapterRepository.findById(Long.parseLong(request.getChapterId()))
                .orElseThrow(() -> new ApiException(ErrorCode.CHAPTER_NOT_FOUND));
        lesson.setIsDeleted(false);
        lesson.setChapter(chapter);
        return lessonMapper.toLessonResponseDTO(lessonRepository.save(lesson));
    }

    @Override
    public LessonResponseDTO updateLesson(Long id, LessonRequestDTO request) {
        var lesson = lessonRepository.findById(id).orElseThrow(() ->
                new ApiException(ErrorCode.LESSON_NOT_EXISTED));
        var chapter = chapterRepository.findById(Long.parseLong(request.getChapterId()))
                .orElseThrow(() -> new ApiException(ErrorCode.CHAPTER_NOT_FOUND));
        lesson.setChapter(chapter);
        return lessonMapper.toLessonResponseDTO(lessonRepository.save(lesson));
    }

    @Override
    public void deleteLesson(Long id) {
        var user = lessonRepository.findById(id).orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_FOUND));
        user.setIsDeleted(true);
        lessonRepository.save(user);
    }

    @Override
    public void updateOrder(List<OrderDTO> lessonOrderList) {
        List<LessonEntity> lessonsToUpdate = lessonOrderList.stream()
                .map(dto -> lessonRepository.findById(Long.parseLong(dto.getId()))
                        .map(lesson -> {
                            lesson.setLessonOrder(Integer.parseInt(dto.getOrder()));
                            return lesson;
                        })
                        .orElseThrow(() -> new ApiException(ErrorCode.LESSON_NOT_EXISTED)))
                .collect(Collectors.toList());
        lessonRepository.saveAll(lessonsToUpdate);
    }
}
