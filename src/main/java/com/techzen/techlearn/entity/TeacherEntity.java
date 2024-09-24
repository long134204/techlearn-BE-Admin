package com.techzen.techlearn.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "is_deleted = false")
@Table(name = "tbl_teacher")
public class TeacherEntity extends BaseEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(length = 7)
    private String color;

    @Column(columnDefinition = "TEXT")
    private String avatar;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @ManyToMany
    @JoinTable(
            name = "tbl_course_teacher",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<CourseEntity> courses;



}

