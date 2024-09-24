package com.techzen.techlearn.service.impl;

import com.techzen.techlearn.dto.request.CourseRequestDTO;
import com.techzen.techlearn.dto.response.CourseResponseDTO;
import com.techzen.techlearn.dto.response.PageResponse;
import com.techzen.techlearn.entity.CourseEntity;
import com.techzen.techlearn.entity.TeacherEntity;
import com.techzen.techlearn.enums.ErrorCode;
import com.techzen.techlearn.exception.ApiException;
import com.techzen.techlearn.mapper.CourseMapper;
import com.techzen.techlearn.repository.CourseRepository;
import com.techzen.techlearn.repository.TeacherRepository;
import com.techzen.techlearn.service.CourseService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CourseServiceImpl implements CourseService {

    CourseRepository courseRepository;
    CourseMapper courseMapper;
    TeacherRepository teacherRepository;

    @Override
    public PageResponse<?> getAllCourse(int page, int size) {
        Pageable pageable = PageRequest.of(page > 0 ? page - 1 : 0, size);
        Page<CourseEntity> course = courseRepository.findAll(pageable);
        List<CourseResponseDTO> list = course.map(courseMapper::toCourseResponseDTO)
                .stream().collect(Collectors.toList());
        return PageResponse.builder()
                .page(page)
                .pageSize(size)
                .totalPage(course.getTotalPages())
                .items(list)
                .build();
    }

    @Override
    public CourseResponseDTO getCourseById(Long id) {
        var course = courseRepository.findById(id).orElseThrow(() ->
                new ApiException(ErrorCode.COURSE_NOT_EXISTED));
        return courseMapper.toCourseResponseDTO(course);
    }

    @Override
    public CourseResponseDTO addCourse(CourseRequestDTO request) {
        var course = courseMapper.toCourseEntity(request);
        course.setIsDeleted(false);
        List<TeacherEntity> teachers = request.getTeachers().stream()
                .map(teacherDto -> teacherRepository.findById(teacherDto.getId()).orElseThrow(() ->
                        new ApiException(ErrorCode.TEACHER_NOT_EXISTED)))
                .collect(Collectors.toList());


        course.setTeachers(teachers);

        return courseMapper.toCourseResponseDTO(
                courseRepository.save(course)
        );
    }


    @Override
    public CourseResponseDTO updateCourse(Long id, CourseRequestDTO request) {
        courseRepository.findById(id).orElseThrow(() ->
                new ApiException(ErrorCode.COURSE_NOT_EXISTED));

        var courseMap = courseMapper.toCourseEntity(request);
        courseMap.setId(id);
        courseMap.setIsDeleted(false);
         List<TeacherEntity> teachers = request.getTeachers().stream()
                .map(teacherDto -> teacherRepository.findById(teacherDto.getId()).orElseThrow(() ->
                        new ApiException(ErrorCode.TEACHER_NOT_EXISTED)))
                .collect(Collectors.toList());

        return courseMapper.toCourseResponseDTO(courseRepository.save(courseMap));
    }


    @Override
    public void deleteCourse(Long id) {
        var course = courseRepository.findById(id).orElseThrow(() ->
                new ApiException(ErrorCode.COURSE_NOT_EXISTED));
        course.setIsDeleted(true);
        courseRepository.save(course);
    }
}
