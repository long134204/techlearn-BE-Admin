package com.techzen.techlearn.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LessonResponseDTO {
    String id;
    String title;
    String type;
    String lessonOrder;
    String content;
    String videoUrl;
    String contentRefer;
    String chapter;
}
