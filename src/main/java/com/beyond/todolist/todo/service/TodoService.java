package com.beyond.todolist.todo.service;

import com.beyond.todolist.todo.domain.Todo;
import com.beyond.todolist.todo.domain.TodoRepository;
import com.beyond.todolist.todo.dto.TodoRegisterRequestDTO;
import com.beyond.todolist.todo.dto.TodoUpdateRequestDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@Transactional
public class TodoService {

    private final TodoRepository todoRepository;

    public Optional<Todo> registerTodo(TodoRegisterRequestDTO todoRegisterRequestDTO) {

        Todo todo = Todo.builder()
                .content(todoRegisterRequestDTO.getContent())
                .status(todoRegisterRequestDTO.getStatus())
                .build();

        return Optional.of(todoRepository.save(todo));
    }

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Optional<Void> updateTodo(Long id, TodoUpdateRequestDTO todoUpdateRequestDTO) {

        Todo todo = todoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("존재하지 않습니다.: " + id)
        );

        todo.update(todoUpdateRequestDTO);

        todoRepository.save(todo);

        return Optional.empty();
    }
}
