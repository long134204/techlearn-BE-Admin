package com.techzen.techlearn.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Uncategorized error", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1006, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "You do not have permission", HttpStatus.FORBIDDEN),
    GET_SUCCESSFUL(1010, "Get successful", HttpStatus.OK),
    ADD_SUCCESSFUL(1011, "Add successful", HttpStatus.OK),
    DELETE_SUCCESSFUL(1012, "Delete successful", HttpStatus.OK),
    UPDATE_SUCCESSFUL(1013, "Update successful", HttpStatus.OK),
    INVALID_DATA(1014, "Invalid data", HttpStatus.BAD_REQUEST),
    REVIEW_NOT_FOUND(1020, "Review not found", HttpStatus.BAD_REQUEST),

    // user code user : 110*
    USER_EXISTED(1101, "User existed", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1102, "Username must be at least {min} characters", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1103, "User not found", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1104, "User not existed", HttpStatus.NOT_FOUND),
    FULL_NAME_INVALID(1105, "Full name must be not blank", HttpStatus.BAD_REQUEST),
    AGE_INVALID(1106, "Your age must be at least {min}", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1107, "Password must be at least {min} characters", HttpStatus.BAD_REQUEST),

    // prompt code prompt : 120*
    PROMPT_STRUCTURE(1201, "Prompt structure must be not blank", HttpStatus.BAD_REQUEST),
    PROMPT_STRUCTURE_NOT_FOUND(1202, "Prompt structure not found", HttpStatus.BAD_REQUEST),

    // error code github : 130*
    GITHUB_LINK(1301, "Github link must be not blank", HttpStatus.BAD_REQUEST),
    GITHUB_NOT_FOUND(1302, "GitHub link not found or invalid! ", HttpStatus.BAD_REQUEST),
    GITHUB_API_ERROR(1303, "Error calling GitHub API", HttpStatus.INTERNAL_SERVER_ERROR),

    // error code Courser : 140*
    COURSE_NOT_FOUND(1401, "Course not found", HttpStatus.BAD_REQUEST),
    ASSIGNMENT_NOT_FOUND(1402, "Assignment not found", HttpStatus.BAD_REQUEST),

    INVALID_DOB(1008, "Your age must be at least {min}", HttpStatus.BAD_REQUEST),

    // error code Teacher : 150*
    TEACHER_NOT_EXISTED(1015, "Teacher not existed", HttpStatus.NOT_FOUND),

    // error code calendar : 160*
    TIME_NOT_SUITABLE(1016, "time start must time end equals ten minutes", HttpStatus.NOT_FOUND),
    TEACHER_CALENDAR_DATE_APPOINTMENT_EXISTED(1017, "teacher or calendar or date appointment  existed",
            HttpStatus.BAD_REQUEST),
    NAME_TEACHER_OR_TECHNICAL_AND_CURRENT_DATE_NOT_EXISTED(1018, "technical or teacher or current date not existed",
            HttpStatus.BAD_REQUEST),
    DATE_APPOINTMENT_NOT_SUITABLE(1019, "This smaller set date is now", HttpStatus.BAD_REQUEST),
    TIME_START_SUITABLE(1020, "This smaller set time is now", HttpStatus.BAD_REQUEST),
    CALENDAR_NOT_EXISTED(1021, "Calendar not existed", HttpStatus.NOT_FOUND),

    // error code course: 1700**
    COURSE_NOT_EXISTED(170001, "Course not existed", HttpStatus.NOT_FOUND),
    COURSE_NAME_INVALID(170002, "Course name must be not blank", HttpStatus.NOT_FOUND),
    COURSE_PRICE_INVALID(170003, "Course price must be not blank", HttpStatus.NOT_FOUND),
    COURSE_THUMBNAIL_URL_INVALID(170004, "Course thumbnail url must be not blank", HttpStatus.NOT_FOUND),
    COURSE_POINT_INVALID(170005, "Course point must be not blank", HttpStatus.NOT_FOUND),
    COURSE_DESCRIPTION_INVALID(170006, "Course description must be not blank", HttpStatus.NOT_FOUND),
    COURSE_UNIT_INVALID(170007, "Course currency unit must be not blank", HttpStatus.NOT_FOUND),
    COURSE_IS_ACTIVE_INVALID(170008, "Course is active must be not blank", HttpStatus.NOT_FOUND),
    COURSE_IS_PUBLIC_INVALID(170009, "Course is public must be not blank", HttpStatus.NOT_FOUND),
    COURSE_CURRENCY_UNIT_INVALID(170010, "The course currency must be USD or VND", HttpStatus.NOT_FOUND),
    COURSE_IS_ACTIVE_INVALID_TYPE(170011, "The course is active must be false or true", HttpStatus.NOT_FOUND),
    COURSE_IS_PUBLIC_INVALID_TYPE(170012, "The course is public must be false or true", HttpStatus.NOT_FOUND),

    // Error code for chapter: 1800**
    CHAPTER_NOT_EXISTED(180001, "Chapter not existed", HttpStatus.NOT_FOUND),
    CHAPTER_NAME_INVALID(180002, "Chapter name must be not blank", HttpStatus.BAD_REQUEST),
    CHAPTER_ORDER_INVALID(180003, "Chapter chapter_order must be a valid number", HttpStatus.BAD_REQUEST),
    CHAPTER_IS_PUBLIC_INVALID(180004, "Chapter public status must be not blank", HttpStatus.BAD_REQUEST),
    CHAPTER_IS_DELETED_INVALID(180005, "Chapter deletion status must be false or true", HttpStatus.BAD_REQUEST),
    CHAPTER_COURSE_ID_INVALID(180006, "Chapter must be associated with a valid course", HttpStatus.BAD_REQUEST),
    CHAPTER_IS_PUBLIC_INVALID_TYPE(180007, "Chapter public status must be false or true", HttpStatus.BAD_REQUEST),
    CHAPTER_NOT_FOUND(180008, "Chapter not found", HttpStatus.NOT_FOUND),

    // error code lesson: 1900**
    LESSON_NOT_EXISTED(190001, "Lesson not existed", HttpStatus.NOT_FOUND),
    LESSON_TITLE_INVALID(190002, "Lesson title must be not blank", HttpStatus.NOT_FOUND),
    LESSON_TYPE_INVALID(190003, "Lesson type must be not blank", HttpStatus.NOT_FOUND),
    LESSON_ORDER_INVALID(190004, "Lesson order url must be not blank", HttpStatus.NOT_FOUND),
    LESSON_CONTENT_INVALID(190005, "Lesson content must be not blank", HttpStatus.NOT_FOUND),
    LESSON_VIDEO_URL_INVALID(190006, "Lesson video url must be not blank", HttpStatus.NOT_FOUND),
    COURSE_CONTENT_REFER_INVALID(190007, "Lesson content refer unit must be not blank", HttpStatus.NOT_FOUND),
    COURSE_TYPE_INVALID_TYPE(190008, "The Lesson type must be READINGS, LECTURES or EXERCISES", HttpStatus.NOT_FOUND),
    CHAPTER_ID_INVALID(190009, "Chapter id must be not blank", HttpStatus.NOT_FOUND),

    ;
    Integer code;
    String message;
    HttpStatusCode statusCode;
}
