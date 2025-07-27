package com.lms.exam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ExamException extends RuntimeException {
    public ExamException(String message) {
        super(message);
    }

    public ExamException(String message, Throwable ex) {
        super(message, ex);
    }
}
