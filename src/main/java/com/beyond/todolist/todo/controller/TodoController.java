package com.beyond.todolist.todo.controller;

import com.beyond.todolist.common.response.BaseResponse;
import com.beyond.todolist.todo.dto.TodoReq;
import com.beyond.todolist.todo.entity.Todo;
import com.beyond.todolist.todo.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/create")
    public BaseResponse<String> createTodo(@RequestBody @Valid TodoReq todoReq) {

        todoService.createTodo(todoReq);

        return new BaseResponse<>("Todo 등록 완료");
    }

    @GetMapping("/list")
    public BaseResponse<List<Todo>> GetAllTodos() {
        List<Todo> todos = todoService.getAllTodos();

        return new BaseResponse<>(todos);
    }

    @PutMapping("/update/{id}")
    public BaseResponse<String> updateTodo(@PathVariable("id") Long id, @RequestBody @Valid TodoReq todoReq) {

        todoService.updateTodo(id, todoReq);

        return new BaseResponse<>("Todo 수정 완료");
    }

    @DeleteMapping("/delete/{id}")
    public BaseResponse<String> deleteTodo(@PathVariable("id") Long id) {

        todoService.deleteTodo(id);

        return new BaseResponse<>("Todo 삭제 완료");
    }
}
