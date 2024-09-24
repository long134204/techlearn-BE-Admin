package com.techzen.techlearn.service.impl;

import com.techzen.techlearn.dto.request.TeacherRequestDTO;
import com.techzen.techlearn.dto.response.PageResponse;
import com.techzen.techlearn.dto.response.TeacherResponseDTO;
import com.techzen.techlearn.entity.TeacherEntity;
import com.techzen.techlearn.enums.ErrorCode;
import com.techzen.techlearn.exception.ApiException;
import com.techzen.techlearn.mapper.TeacherMapper;
import com.techzen.techlearn.repository.TeacherRepository;
import com.techzen.techlearn.service.TeacherService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TeacherServiceImpl implements TeacherService {
    TeacherRepository teacherRepository;
    TeacherMapper teacherMapper;
    @Override
    public PageResponse<?> findAll(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page > 0 ? page - 1 : 0, pageSize);
        Page<TeacherEntity> teachers = teacherRepository.findAll(pageable);
        List<TeacherResponseDTO> list = teachers.map(teacherMapper::toTeacherResponseDTO).stream().collect(Collectors.toList());
        return PageResponse.builder()
                .page(page)
                .pageSize(pageSize)
                .totalPage(teachers.getTotalPages())
                .items(list)
                .build();
    }


    @Override
    public TeacherResponseDTO addTeacher(TeacherRequestDTO request) {
        TeacherEntity teacher = teacherMapper.toTeacherEntity(request);
        teacher.setIsDeleted(false);
        if (teacher.getId() == null) {
            teacher.setId(UUID.randomUUID());
        }
        return teacherMapper.toTeacherResponseDTO(teacherRepository.save(teacher));
    }

    @Override
    public TeacherResponseDTO getTeacherById(UUID id) {
        TeacherEntity teacher = teacherRepository.findTeacherById(id).orElseThrow(() -> new ApiException(ErrorCode.TEACHER_NOT_EXISTED));
        return teacherMapper.toTeacherResponseDTO(teacher);
    }

    @Override
    public TeacherResponseDTO updateTeacher(UUID id, TeacherRequestDTO request) {
        teacherRepository.findById(id).orElseThrow(() -> new ApiException(ErrorCode.TEACHER_NOT_EXISTED));
        var teacherMap = teacherMapper.toTeacherEntity(request);
        teacherMap.setId(id);
        teacherMap.setIsDeleted(false);
       return teacherMapper.toTeacherResponseDTO(teacherRepository.save(teacherMap));
    }

    @Override
    public void deleteTeacher(UUID id) {
        var teacher = teacherRepository.findById(id).orElseThrow(() -> new ApiException(ErrorCode.TEACHER_NOT_EXISTED));
        teacher.setIsDeleted(true);
        teacherRepository.save(teacher);
    }
}
