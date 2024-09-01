package com.beyond.todolist.todo.service;

import com.beyond.todolist.todo.dto.CreateTodoReq;
import com.beyond.todolist.todo.entity.Todo;
import com.beyond.todolist.todo.entity.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TodoService {

    private final TodoRepository todoRepository;

    public Optional<Todo> createTodo(CreateTodoReq createTodoReq){
        Todo todo = Todo.builder()
                .content(createTodoReq.getContent())
                .status(createTodoReq.getStatus())
                .build();

        return Optional.of(todoRepository.save(todo));
    }


}
