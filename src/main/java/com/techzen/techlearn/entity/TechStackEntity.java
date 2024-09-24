package com.techzen.techlearn.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Where(clause = "is_deleted = false")
@Table(name = "tbl_tech_stack")
public class TechStackEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @ManyToMany
    @JoinTable(name = "tbl_course_tech_stack",
            joinColumns = @JoinColumn(name = "id_tech_stack"),
    inverseJoinColumns = @JoinColumn(name = "is_course"))
    List<CourseEntity> courses = new ArrayList<>();
}
