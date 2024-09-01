package com.beyond.todolist.todo.service;

import com.beyond.todolist.todo.dto.TodoReq;
import com.beyond.todolist.todo.entity.Todo;
import com.beyond.todolist.todo.entity.TodoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TodoService {

    private final TodoRepository todoRepository;

    public Optional<Todo> createTodo(TodoReq todoReq){
        Todo todo = Todo.builder()
                .content(todoReq.getContent())
                .status(todoReq.getStatus())
                .build();

        return Optional.of(todoRepository.save(todo));
    }


    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public void updateTodo(Long id, TodoReq todoReq) {

        Todo todo = todoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("존재하지 않습니다.: " + id));

        todo.setContent(todoReq.getContent());
        todo.setStatus(todoReq.getStatus());

        todoRepository.save(todo);
    }
}
