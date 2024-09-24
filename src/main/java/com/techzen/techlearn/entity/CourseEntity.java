package com.techzen.techlearn.entity;

import com.techzen.techlearn.enums.CurrencyUnit;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Where(clause = "is_deleted = false")
@Table(name = "tbl_course")
public class CourseEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "price")
    BigDecimal price;

    @Column(name = "thumbnail_url")
    String thumbnailUrl;

    @Column(name = "point")
    Integer point;

    @Column(name = "description", columnDefinition = "NTEXT")
    String description;

    @Column(name = "currency_unit")
    @Enumerated(EnumType.STRING)
    CurrencyUnit currencyUnit;

    @Column(name = "is_active")
    Boolean isActive;

    @Column(name = "is_public")
    Boolean isPublic;

    @Column(name = "is_deleted")
    Boolean isDeleted;

    @ManyToMany(mappedBy = "courses")
    List<TechStackEntity> techStackEntities;

    @ManyToMany
    @JoinTable(
            name = "tbl_course_teacher",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    List<TeacherEntity> teachers;

}
