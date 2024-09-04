package com.beyond.todolist.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum TodoMessageType {

    BAD_REQUEST("Check API request URL protocol, parameter, etc. for errors", HttpStatus.BAD_REQUEST),
    ARGUMENT_NOT_VALID("The format of the argument passed is invalid.", HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR("An error occurred inside the server.", HttpStatus.INTERNAL_SERVER_ERROR),
    TODO_NOT_FOUND("Todo not found", HttpStatus.NOT_FOUND),
    METHOD_NOT_ALLOWED("your request method not allowed", HttpStatus.METHOD_NOT_ALLOWED),

    ;
    private final String message;
    private final HttpStatus status;
}
