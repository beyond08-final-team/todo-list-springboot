package com.beyond.todolist.application.service;

import com.beyond.todolist.exception.TodoAppException;
import com.beyond.todolist.exception.TodoMessageType;
import com.beyond.todolist.infrastructure.mariadb.entity.TodoEntity;
import com.beyond.todolist.infrastructure.mariadb.repository.TodoCrudRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TodoService implements TodoOperationUseCase, TodoReadUseCase{

    private final TodoCrudRepository todoCrudRepository;

    @Override
    public FindTodoResult saveTodo(TodoCreateCommand command) {
        log.info("[TodoService - saveTodo] command = {}", command);
        return FindTodoResult.findByTodoEntity(
                todoCrudRepository.save(TodoEntity.createTodo(command))
        );
    }

    @Override
    public List<FindTodoResult> getTodos(TodoFindQuery query) {
        log.info("[TodoService - getTodos] query = {}", query);
        return List.of();
    }

    @Override
    public FindTodoResult updateTodoContent(TodoContentUpdateCommand command) {
        log.info("[TodoService - updateTodoContent] command = {}", command);
        TodoEntity todo = retrieveTodoEntityById(command.getId());

        log.info("[TodoService - updateTodoContent] before Update targetId = {} targetContent = {} targetUpdatedDateTime = {}",
                todo.getId(),
                todo.getContent(),
                todo.getUpdatedDateTime()
        );

        todo.updateContent(command.getContent());

        log.info("[TodoService - updateTodoContent] after Update targetId = {} targetContent = {} targetUpdatedDateTime = {}",
                todo.getId(),
                todo.getContent(),
                todo.getUpdatedDateTime()
        );

        return FindTodoResult.findByTodoEntity(todo);
    }

    @Override
    public FindTodoResult updateTodoStatus(TodoStatusUpdateCommand command) {

        TodoEntity todo = retrieveTodoEntityById(command.getId());

        todo.updateStatus(command.getStatus());

        log.info("[TodoService - updateTodoStatus] after Update targetId = {} targetStatus = {} targetUpdatedDateTime = {}",
                todo.getId(),
                todo.getStatus(),
                todo.getUpdatedDateTime()
        );

        return FindTodoResult.findByTodoEntity(todo);
    }

    @Override
    public void deleteTodo(Long id) {
        TodoEntity todo = retrieveTodoEntityById(id);
        todoCrudRepository.delete(todo);
    }

    private TodoEntity retrieveTodoEntityById(Long id) {
        return todoCrudRepository.findById(id)
                .orElseThrow(() -> new TodoAppException(TodoMessageType.TODO_NOT_FOUND));
    }
}
