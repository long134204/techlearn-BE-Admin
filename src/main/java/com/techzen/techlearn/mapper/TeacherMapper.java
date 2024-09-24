package com.techzen.techlearn.mapper;

import com.techzen.techlearn.dto.request.TeacherRequestDTO;
import com.techzen.techlearn.dto.response.TeacherResponseDTO;
import com.techzen.techlearn.entity.TeacherEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    TeacherEntity toTeacherEntity(TeacherRequestDTO dto);

    TeacherResponseDTO toTeacherResponseDTO(TeacherEntity entity);
}

