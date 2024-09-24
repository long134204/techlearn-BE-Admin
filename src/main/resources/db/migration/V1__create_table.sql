CREATE TABLE tbl_course
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_by    VARCHAR(255) NULL,
    created_date  datetime NULL,
    modified_date datetime NULL,
    modified_by   VARCHAR(255) NULL,
    name          VARCHAR(255) NULL,
    price         DECIMAL NULL,
    thumbnail_url VARCHAR(255) NULL,
    point         INT NULL,
    `description` LONGTEXT NULL,
    currency_unit VARCHAR(255) NULL,
    is_active     BIT(1) NULL,
    is_public     BIT(1) NULL,
    is_deleted    BIT(1) NULL,
    CONSTRAINT pk_tbl_course PRIMARY KEY (id)
);

CREATE TABLE tbl_course_teacher
(
    course_id  BIGINT NOT NULL,
    teacher_id BINARY(16) NOT NULL,
    CONSTRAINT fk_course FOREIGN KEY (course_id) REFERENCES tbl_course(id) ,
    CONSTRAINT fk_teacher FOREIGN KEY (teacher_id) REFERENCES tbl_teacher(id),
    CONSTRAINT pk_tbl_course_teacher PRIMARY KEY (course_id, teacher_id)
);


CREATE TABLE tbl_chapter
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_by    VARCHAR(255) NULL,
    created_date  datetime NULL,
    modified_date datetime NULL,
    modified_by   VARCHAR(255) NULL,
    name          VARCHAR(255) NULL,
    `order`       INT NULL,
    is_public     BIT(1) NULL,
    is_deleted    BIT(1) NULL,
    course_id     BIGINT NULL,
    CONSTRAINT pk_tbl_chapter PRIMARY KEY (id)
);

ALTER TABLE tbl_chapter
    ADD CONSTRAINT FK_TBL_CHAPTER_ON_COURSE FOREIGN KEY (course_id) REFERENCES tbl_course (id);

CREATE TABLE tbl_lesstion
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_by    VARCHAR(255) NULL,
    created_date  datetime NULL,
    modified_date datetime NULL,
    modified_by   VARCHAR(255) NULL,
    title         VARCHAR(255) NULL,
    type          VARCHAR(255) NULL,
    `order`       INT NULL,
    content       LONGTEXT NULL,
    video_url     VARCHAR(255) NULL,
    content_refer LONGTEXT NULL,
    is_deleted    BIT(1) NULL,
    chapter_id    BIGINT NULL,
    CONSTRAINT pk_tbl_lesstion PRIMARY KEY (id)
);

ALTER TABLE tbl_lesstion
    ADD CONSTRAINT FK_TBL_LESSTION_ON_CHAPTER FOREIGN KEY (chapter_id) REFERENCES tbl_chapter (id);

CREATE TABLE tbl_course_tech_stack
(
    id_tech_stack BIGINT NOT NULL,
    is_course     BIGINT NOT NULL
);

CREATE TABLE tbl_tech_stack
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_by    VARCHAR(255) NULL,
    created_date  datetime NULL,
    modified_date datetime NULL,
    modified_by   VARCHAR(255) NULL,
    name          VARCHAR(255) NULL,
    is_deleted    BIT(1) NULL,
    CONSTRAINT pk_tbl_tech_stack PRIMARY KEY (id)
);

ALTER TABLE tbl_course_tech_stack
    ADD CONSTRAINT fk_tblcoutecsta_on_course_entity FOREIGN KEY (is_course) REFERENCES tbl_course (id);

ALTER TABLE tbl_course_tech_stack
    ADD CONSTRAINT fk_tblcoutecsta_on_tech_stack_entity FOREIGN KEY (id_tech_stack) REFERENCES tbl_tech_stack (id);