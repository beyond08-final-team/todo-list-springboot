package com.beyond.todolist.controller;

import com.beyond.todolist.model.Todo;
import com.beyond.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todoList")
@CrossOrigin(origins = "http://localhost:3000")  // 프론트엔드 주소
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping("/save")
    public Todo saveTodo(@RequestBody Todo todo) {
        return todoService.saveTodo(todo);
    }

    @PutMapping("/update/{id}")
    public Todo updateTodo(@PathVariable Long id, @RequestBody Todo todo) {
        return todoService.updateTodo(id, todo);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
    }

    @GetMapping("/list")
    public List<Todo> getAllTodos() {
        return todoService.getAllTodos();
    }
}
