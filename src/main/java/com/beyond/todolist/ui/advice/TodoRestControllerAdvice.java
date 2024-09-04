package com.beyond.todolist.ui.advice;

import com.beyond.todolist.exception.TodoAppException;
import com.beyond.todolist.exception.TodoMessageType;
import com.beyond.todolist.ui.view.ApiErrorView;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.ClientAbortException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;

@Slf4j
@RestControllerAdvice
public class TodoRestControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ClientAbortException.class)
    public ResponseEntity<?> clientAbortException() {
        log.error("clientAbortException");
        return new ResponseEntity<>(new ApiErrorView(Collections.singletonList(TodoMessageType.INTERNAL_SERVER_ERROR)),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TodoAppException.class)
    public ResponseEntity<?> onMessageException(TodoAppException ex) {
        return new ResponseEntity<>(new ApiErrorView(ex), ex.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return new ResponseEntity<>(new ApiErrorView(TodoMessageType.METHOD_NOT_ALLOWED, ex.getMessage()), TodoMessageType.METHOD_NOT_ALLOWED.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {

        if (ex instanceof MethodArgumentNotValidException) {
            return new ResponseEntity<>(new ApiErrorView(TodoMessageType.ARGUMENT_NOT_VALID,
                    TodoMessageType.ARGUMENT_NOT_VALID.getMessage()), statusCode);
        }

        log.info("handleExceptionInternal");
        log.info(String.valueOf(ex));
        log.info(String.valueOf(ex.getClass()));

        return new ResponseEntity<>(new ApiErrorView(TodoMessageType.INTERNAL_SERVER_ERROR, ex.getMessage()), statusCode);

    }
}
