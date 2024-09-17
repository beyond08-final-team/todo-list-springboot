package com.beyond.todolist.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TodoExceptionHandler {
//    내가 정의한 커스텀 에러에서 Controller에서 throw를 던지면 이렇게 처리하겠다고 알려줌.

    @ExceptionHandler(TodoException.class) // TodoException 발생 시 부름
    public ResponseEntity<String> handleTodoException(TodoException e) {
//        예외 메세지와 함께 BAD_REQUEST 상태 반환
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
