package com.techzen.techlearn.service.impl;

import com.techzen.techlearn.dto.request.ChapterRequestDTO;
import com.techzen.techlearn.dto.request.OrderDTO;
import com.techzen.techlearn.dto.response.ChapterResponseDTO;
import com.techzen.techlearn.dto.response.PageResponse;
import com.techzen.techlearn.entity.ChapterEntity;
import com.techzen.techlearn.enums.ErrorCode;
import com.techzen.techlearn.exception.ApiException;
import com.techzen.techlearn.mapper.ChapterMapper;
import com.techzen.techlearn.repository.ChapterRepository;
import com.techzen.techlearn.repository.CourseRepository;
import com.techzen.techlearn.service.ChapterService;
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
public class ChapterServiceImpl implements ChapterService {

    ChapterRepository chapterRepository;
    ChapterMapper chapterMapper;
    CourseRepository courseRepository;

    @Override
    public ChapterResponseDTO getChapterById(Long id) {
        ChapterEntity chapterEntity = chapterRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.CHAPTER_NOT_EXISTED));
        return chapterMapper.toChapterResponseDTO(chapterEntity);
    }

    @Override
    public ChapterResponseDTO addChapter(ChapterRequestDTO request) {
        var chapterEntity = chapterMapper.toChapterEntity(request);
        var course = courseRepository.findById(Long.parseLong(request.getCourseId()))
                .orElseThrow(() -> new ApiException(ErrorCode.COURSE_NOT_EXISTED));
        chapterEntity.setCourse(course);
        chapterEntity.setIsDeleted(false);
        return chapterMapper.toChapterResponseDTO(chapterRepository.save(chapterEntity));
    }

    @Override
    public ChapterResponseDTO updateChapter(Long id, ChapterRequestDTO request) {
        var chapterEntity = chapterRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.CHAPTER_NOT_EXISTED));
        var course = courseRepository.findById(Long.parseLong(request.getCourseId()))
                .orElseThrow(() -> new ApiException(ErrorCode.COURSE_NOT_EXISTED));
        //chapterMapper.updateChapterEntityFromDTO(request, chapterEntity);
        chapterEntity.setCourse(course);
        //chapterEntity.setIsDeleted(false);
        return chapterMapper.toChapterResponseDTO(chapterRepository.save(chapterEntity));
    }

    @Override
    public void deleteChapter(Long id) {
        ChapterEntity chapterEntity = chapterRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.CHAPTER_NOT_EXISTED));
        chapterEntity.setIsDeleted(true);
        chapterRepository.save(chapterEntity);
    }

    @Override
    public PageResponse<?> getAllChapters(int page, int pageSize, Long id) {
        Pageable pageable = PageRequest.of(page > 0 ? page - 1 : 0, pageSize,
                Sort.by("chapterOrder"));
        Page<ChapterEntity> chapter = chapterRepository.findAllByCourseId(id, pageable);
        List<ChapterResponseDTO> list = chapter.map(chapterMapper::toChapterResponseDTO)
                .stream().collect(Collectors.toList());
        return PageResponse.builder()
                .page(page)
                .pageSize(pageSize)
                .totalPage(chapter.getTotalPages())
                .items(list)
                .build();
    }

    @Override
    public void updateOrder(List<OrderDTO> orderDTOS) {
        List<ChapterEntity> lessonsToUpdate = orderDTOS.stream()
                .map(dto -> chapterRepository.findById(Long.parseLong(dto.getId()))
                        .map(lesson -> {
                            lesson.setChapterOrder(Integer.parseInt(dto.getOrder()));
                            return lesson;
                        })
                        .orElseThrow(() -> new ApiException(ErrorCode.LESSON_NOT_EXISTED)))
                .collect(Collectors.toList());
        chapterRepository.saveAll(lessonsToUpdate);
    }
}
