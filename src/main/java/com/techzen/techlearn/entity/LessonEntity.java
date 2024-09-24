package com.techzen.techlearn.entity;

import com.techzen.techlearn.enums.TypeLesson;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Where(clause = "is_deleted = false")
@Table(name = "tbl_lesson")
public class LessonEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "title")
    String title;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    TypeLesson type;

    @Column(name = "lesson_order")
    Integer lessonOrder;

    @Column(name = "content", columnDefinition = "NTEXT")
    String content;

    @Column(name = "video_url")
    String videoUrl;

    @Column(name = "content_refer", columnDefinition = "NTEXT")
    String contentRefer;

    @Column(name = "is_deleted")
    Boolean isDeleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chapter_id")
    private ChapterEntity chapter;
}
