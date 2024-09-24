package com.techzen.techlearn.exception;

import com.techzen.techlearn.dto.response.ApiResponse;
import com.techzen.techlearn.enums.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ApiException.class)
    ResponseEntity<?> handlingAppException(ApiException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        return ResponseEntity.status(errorCode.getStatusCode()).body(
                ApiResponse.builder()
                        .code(errorCode.getCode())
                        .message(errorCode.getMessage())
                        .build()
        );
    }

/*    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<?> handlingValidException(MethodArgumentNotValidException exception) {
        var error = new ApiResponse<>();
        String errorName = ErrorCode
                .valueOf(exception.getBindingResult().getFieldErrors().get(0).getDefaultMessage())
                .getMessage();
        error.setCode(ErrorCode.INVALID_DATA.getCode());
        error.setMessage(errorName);
        exception.getBindingResult().getFieldErrors().forEach(fieldError ->
                error.additionalProperty(fieldError.getField(), fieldError.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }*/

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<?> handlingValidException(MethodArgumentNotValidException exception) {
        var error = new ApiResponse<>();
        String errorName = ErrorCode
                .valueOf(exception.getBindingResult().getFieldErrors().get(0).getDefaultMessage())
                .getMessage();
        error.setCode(ErrorCode.INVALID_DATA.getCode());
        error.setMessage(errorName);
        exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
            error.additionalProperty(fieldError.getField(), errorName);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<?> handlingAccessDeniedException(AccessDeniedException exception) {
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;
        return ResponseEntity.status(errorCode.getStatusCode())
                .body(ApiResponse.builder()
                        .code(errorCode.getCode())
                        .message(errorCode.getMessage())
                        .build());
    }
}