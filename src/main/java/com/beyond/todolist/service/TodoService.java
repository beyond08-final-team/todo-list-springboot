package com.beyond.todolist.service;

import com.beyond.todolist.model.Todo;

import java.util.List;

public interface TodoService {
    Todo saveTodo(Todo todo);
    Todo updateTodo(Long id, Todo todo);
    void deleteTodo(Long id);
    List<Todo> getAllTodos();
}
