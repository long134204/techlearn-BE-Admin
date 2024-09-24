package com.techzen.techlearn.dto.response;

import com.techzen.techlearn.enums.CurrencyUnit;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseResponseDTO {
    Long id;
    String name;
    BigDecimal price;
    String thumbnailUrl;
    Integer point;
    String description;
    CurrencyUnit currencyUnit;
    Boolean isActive;
    Boolean isPublic;
    List<String> techStack;
    List<TeacherResponseDTO> teachers;
}
