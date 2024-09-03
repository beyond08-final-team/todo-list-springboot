package com.beyond.todolist.application.service;

import com.beyond.todolist.application.domain.TodoStatus;
import com.beyond.todolist.infrastructure.mariadb.entity.TodoEntity;
import com.beyond.todolist.infrastructure.mariadb.repository.TodoCrudRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.IdGenerator;

import java.time.LocalDateTime;

import static com.beyond.todolist.application.service.TodoOperationUseCase.TodoCreateCommand;
import static com.beyond.todolist.application.service.TodoReadUseCase.FindTodoResult;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

    @Mock
    private TodoCrudRepository todoCrudRepository;

    @InjectMocks
    private TodoService todoService;

    @Test
    void saveTodo() {
        // given

        TodoCreateCommand command = TodoCreateCommand.builder()
                .content("hello World")
                .build();

        TodoEntity savedEntity = TodoEntity.createTodo(command);

        given(todoCrudRepository.save(any(TodoEntity.class))).willReturn(savedEntity);

        // when
        FindTodoResult result = todoService.saveTodo(command);

        // then
        then(todoCrudRepository)
                .should()
                .save(
                        argThat(todoEntity ->
                                todoEntity.getContent().equals(savedEntity.getContent()) &&
                                        todoEntity.getStatus() == TodoStatus.InProgress
                        )
                );

        System.out.println(result.toString());
        assertThat(result.getId()).isEqualTo(savedEntity.getId());
        assertThat(result.getContent()).isEqualTo(savedEntity.getContent());
        assertThat(result.getStatus()).isEqualTo(TodoStatus.InProgress); // 기본 상태 확인
        assertThat(result.getCreatedDateTime()).isEqualTo(savedEntity.getCreatedDateTime());
        assertThat(result.getUpdatedDateTime()).isEqualTo(savedEntity.getUpdatedDateTime());
    }

    @Test
    void getTodos() {
    }

    @Test
    void updateTodoContent() {
    }

    @Test
    void updateTodoStatus() {
    }

    @Test
    void deleteTodo() {
    }
}