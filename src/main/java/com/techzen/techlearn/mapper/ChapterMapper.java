package com.techzen.techlearn.mapper;

import com.techzen.techlearn.dto.request.ChapterRequestDTO;
import com.techzen.techlearn.dto.response.ChapterResponseDTO;
import com.techzen.techlearn.entity.ChapterEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ChapterMapper {

    @Mapping(source = "courseId", target = "course.id")
    ChapterEntity toChapterEntity(ChapterRequestDTO chapterRequestDTO);

    @Mapping(source = "course.id", target = "courseId")
    ChapterResponseDTO toChapterResponseDTO(ChapterEntity chapterEntity);

/*    @Mapping(source = "courseId", target = "course.id")
    void updateChapterEntityFromDTO(ChapterRequestDTO chapterRequestDTO, @MappingTarget ChapterEntity chapterEntity);*/
}
