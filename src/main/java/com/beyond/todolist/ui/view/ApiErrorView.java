package com.beyond.todolist.ui.view;

import com.beyond.todolist.exception.TodoAppException;
import com.beyond.todolist.exception.TodoMessageType;
import lombok.Getter;
import lombok.ToString;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
public class ApiErrorView {

    private List<Error> errors;

    public ApiErrorView(List<TodoMessageType> messageTypes) {
        this.errors = messageTypes.stream().map(Error::errorWithMessageType).collect(Collectors.toList());
    }

    public ApiErrorView(TodoMessageType messageType, String message) {
        this.errors = Collections.singletonList(Error.errorWithMessageTypeAndMessage(messageType, message));
    }

    public ApiErrorView(TodoAppException exception) {
        this.errors = Collections.singletonList(Error.errorWithException(exception));
    }


    @Getter
    @ToString
    private static class Error {

        private final String errorType;
        private final String errorMessage;

        public Error(String errorType, String errorMessage) {
            this.errorType = errorType;
            this.errorMessage = errorMessage;
        }

        private Error(TodoAppException todoAppException) {
            this.errorType = ObjectUtils.isEmpty(todoAppException.getType()) ? todoAppException.getStatus().getReasonPhrase() :
                    todoAppException.getType();
            this.errorMessage = todoAppException.getMessage();
        }

        public static Error errorWithMessageType(TodoMessageType message) {
            return new Error(message.name(), message.getMessage());
        }

        public static Error errorWithMessageTypeAndMessage(TodoMessageType messageType, String message) {
            return new Error(messageType.name(), message);
        }


        public static Error errorWithException(TodoAppException ex) {
            return new Error(ex);
        }


    }
}
