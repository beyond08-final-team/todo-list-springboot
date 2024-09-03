package com.beyond.todolist.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public class TodoAppException extends RuntimeException {
    private final String type;
    private final HttpStatus status;

    public TodoAppException(TodoMessageType message) {
        super(message.getMessage());
        this.type = message.name();
        this.status = message.getStatus();
    }
}
