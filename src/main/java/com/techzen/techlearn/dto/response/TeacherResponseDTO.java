package com.techzen.techlearn.dto.response;

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
public class TeacherResponseDTO {

    @JsonProperty("Id")
    UUID id;

    @JsonProperty("OwnerText")
    String name;

    String avatar;

    @JsonProperty("OwnerColor")
    String color;
}

