package com.beyond.todolist.service;

import com.beyond.todolist.model.Todo;
import com.beyond.todolist.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public Todo saveTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public Todo updateTodo(Long id, Todo todo) {
        Todo existingTodo = todoRepository.findById(id).orElseThrow(() -> new RuntimeException("Todo not found"));
        existingTodo.setContent(todo.getContent());
        existingTodo.setStatus(todo.getStatus());
        return todoRepository.save(existingTodo);
    }

    @Override
    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }

    @Override
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }
}
