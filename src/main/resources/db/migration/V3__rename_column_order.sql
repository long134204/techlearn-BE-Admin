ALTER TABLE tbl_chapter
    CHANGE COLUMN `order` chapter_order INT;

ALTER TABLE tbl_lesson
    CHANGE COLUMN `order` lesson_order INT;