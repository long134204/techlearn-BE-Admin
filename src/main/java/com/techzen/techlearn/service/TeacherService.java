package com.techzen.techlearn.service;

import com.techzen.techlearn.dto.request.TeacherRequestDTO;
import com.techzen.techlearn.dto.response.PageResponse;
import com.techzen.techlearn.dto.response.TeacherResponseDTO;

import java.util.UUID;

public interface TeacherService {
    PageResponse<?> findAll(int page, int pageSize);
    TeacherResponseDTO addTeacher(TeacherRequestDTO request);

    TeacherResponseDTO getTeacherById(UUID id);

    TeacherResponseDTO updateTeacher(UUID id, TeacherRequestDTO request);

    void deleteTeacher(UUID id);
}

