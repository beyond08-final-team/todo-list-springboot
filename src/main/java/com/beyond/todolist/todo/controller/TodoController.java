package com.beyond.todolist.todo.controller;

import com.beyond.todolist.common.response.BaseResponse;
import com.beyond.todolist.todo.dto.CreateTodoReq;
import com.beyond.todolist.todo.entity.Todo;
import com.beyond.todolist.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/create")
    public BaseResponse<String> createTodo(@RequestBody CreateTodoReq createTodoReq) {

        todoService.createTodo(createTodoReq);

        return new BaseResponse<>("");
    }
}
