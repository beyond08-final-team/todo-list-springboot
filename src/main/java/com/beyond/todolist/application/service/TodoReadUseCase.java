package com.beyond.todolist.application.service;

import com.beyond.todolist.application.domain.TodoStatus;
import com.beyond.todolist.infrastructure.mariadb.entity.TodoEntity;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

public interface TodoReadUseCase {

    List<FindTodoResult> getTodos(TodoFindQuery query);

    FindTodoResult getTodoById(TodoFindQuery query);

    @Getter
    @Builder
    @ToString
    @EqualsAndHashCode
    class TodoFindQuery {
        private final Long id;
        private final String content;
    }

    @Getter
    @Builder
    @ToString
    @EqualsAndHashCode
    class FindTodoResult {
        private final Long id;
        private final String content;
        private final TodoStatus status;
        private final LocalDateTime createdDateTime;
        private final LocalDateTime updatedDateTime;

        public static FindTodoResult findByTodoEntity(TodoEntity todo) {
            return FindTodoResult.builder()
                    .id(todo.getId())
                    .content(todo.getContent())
                    .status(todo.getStatus())
                    .createdDateTime(todo.getCreatedDateTime())
                    .updatedDateTime(todo.getUpdatedDateTime())
                    .build();
        }
    }
}
