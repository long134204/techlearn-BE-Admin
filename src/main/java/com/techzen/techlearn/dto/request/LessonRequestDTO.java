package com.techzen.techlearn.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LessonRequestDTO {
    @NotBlank(message = "LESSON_TITLE_INVALID")
    String title;
    @NotBlank(message = "LESSON_TYPE_INVALID")
    @Pattern(regexp = "READINGS|LECTURES|EXERCISES", message = "COURSE_TYPE_INVALID_TYPE")
    String type;
    @NotBlank(message = "LESSON_ORDER_INVALID")
    String lessonOrder;
    @NotBlank(message = "LESSON_CONTENT_INVALID")
    String content;
    @NotBlank(message = "LESSON_VIDEO_URL_INVALID")
    String videoUrl;
    @NotBlank(message = "COURSE_CONTENT_REFER_INVALID")
    String contentRefer;
    @NotBlank(message = "CHAPTER_ID_INVALID")
    String chapterId;
}
