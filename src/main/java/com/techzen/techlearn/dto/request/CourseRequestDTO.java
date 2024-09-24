package com.techzen.techlearn.dto.request;

import com.techzen.techlearn.entity.TeacherEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseRequestDTO {

    Long id;
    @NotBlank(message = "COURSE_NAME_INVALID")
    String name;
    @NotBlank(message = "COURSE_PRICE_INVALID")
    String price;
    @NotBlank(message = "COURSE_THUMBNAIL_URL_INVALID")
    String thumbnailUrl;
    @NotBlank(message = "COURSE_POINT_INVALID")
    String point;
    @NotBlank(message = "COURSE_DESCRIPTION_INVALID")
    String description;
    @NotBlank(message = "COURSE_UNIT_INVALID")
    @Pattern(regexp = "VND|USD", message = "COURSE_CURRENCY_UNIT_INVALID")
    String currencyUnit;
    @NotBlank(message = "COURSE_IS_ACTIVE_INVALID")
    @Pattern(regexp = "true|false", message = "COURSE_IS_ACTIVE_INVALID_TYPE")
    String isActive;
    @NotBlank(message = "COURSE_IS_PUBLIC_INVALID")
    @Pattern(regexp = "true|false", message = "COURSE_IS_PUBLIC_INVALID_TYPE")
    String isPublic;
    List<String> techStack;

    List<TeacherEntity> teachers;
}
