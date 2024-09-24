package com.techzen.techlearn.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeacherRequestDTO {

    @JsonProperty("Id")
    UUID id;

    @JsonProperty("OwnerText")
    String name;

    String avatar;

    @JsonProperty("OwnerColor")
    String color;
}
