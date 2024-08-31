package com.beyond.todolist.todo.service;

import com.beyond.todolist.todo.domain.TodoRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@Transactional
public class TodoService {

    private final TodoRepository todoRepository;
}
