package com.beyond.todolist.application.service;

import com.beyond.todolist.application.domain.TodoStatus;
import com.beyond.todolist.application.service.TodoReadUseCase.FindTodoResult;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public interface TodoOperationUseCase {

    FindTodoResult saveTodo(TodoCreateCommand command);

    FindTodoResult updateTodoContent(TodoContentUpdateCommand command);

    FindTodoResult updateTodoStatus(TodoStatusUpdateCommand command);

    void deleteTodo(Long id);

    @Getter
    @Builder
    @ToString
    class TodoCreateCommand {
        private final String content;
    }

    @Getter
    @Builder
    @ToString
    class TodoContentUpdateCommand {
        private Long id;
        private String content;
    }

    @Getter
    @Builder
    @ToString
    class TodoStatusUpdateCommand {
        private Long id;
        private TodoStatus status;
    }
}
