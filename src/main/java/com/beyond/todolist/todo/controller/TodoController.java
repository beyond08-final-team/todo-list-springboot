package com.beyond.todolist.todo.controller;

import com.beyond.todolist.todo.service.TodoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "TodoApp", description = "투두앱 만들기")
@RestController
@RequestMapping("/api/v1/todo")
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class TodoController {

    private final TodoService todoService;
}
