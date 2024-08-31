package com.beyond.todolist.todo.controller;

import com.beyond.todolist.todo.dto.TodoRegisterRequestDTO;
import com.beyond.todolist.todo.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "TodoApp", description = "투두앱 만들기")
@RestController
@RequestMapping("/api/v1/todo")
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class TodoController {

    private final TodoService todoService;

    @Operation(summary = "할 일 리스트를 생성하는 API")
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid TodoRegisterRequestDTO todoRegisterRequestDTO) {

        todoService.registerTodo(todoRegisterRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
