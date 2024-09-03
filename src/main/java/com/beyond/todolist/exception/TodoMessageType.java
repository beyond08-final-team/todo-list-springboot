package com.beyond.todolist.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum TodoMessageType {

    BAD_REQUEST("", HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER("", HttpStatus.INTERNAL_SERVER_ERROR),
    TODO_NOT_FOUND("", HttpStatus.NOT_FOUND),
    ;
    private final String message;
    private final HttpStatus status;
}
