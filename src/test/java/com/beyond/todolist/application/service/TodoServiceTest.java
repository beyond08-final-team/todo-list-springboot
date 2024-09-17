package com.beyond.todolist.application.service;

import com.beyond.todolist.application.domain.TodoStatus;
import com.beyond.todolist.exception.TodoAppException;
import com.beyond.todolist.exception.TodoMessageType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static com.beyond.todolist.application.service.TodoOperationUseCase.TodoCreateCommand;
import static com.beyond.todolist.application.service.TodoReadUseCase.FindTodoResult;
import static com.beyond.todolist.application.service.TodoReadUseCase.TodoFindQuery;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class TodoServiceTest {

    @Autowired
    private TodoOperationUseCase operationUseCase;

    @Autowired
    private TodoReadUseCase todoReadUseCase;

    @Test
    @DisplayName("[save]#1")
    void saveTodoSuccessfully() {
        // given 테스트 준비
        TodoCreateCommand command = TodoCreateCommand.builder()
                .content("hello world")
                .build();

        // when & then
        assertThatCode(() -> operationUseCase.saveTodo(command))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("[save]#2")
    void saveTodoSuccessfullyAndReturnCorrectResponse() {
        // given
        TodoCreateCommand command = TodoCreateCommand.builder()
                .content("hello world")
                .build();

        // when
        FindTodoResult result = operationUseCase.saveTodo(command);

        // then
        assertThat(result.getContent()).isEqualTo(command.getContent());
        assertThat(result.getStatus()).isEqualTo(TodoStatus.InProgress);
        assertThat(result.getCreatedDateTime()).isNotNull();

    }



    @Test
    @DisplayName("투두 id로 조회 성공")
    void getTodoByIdSuccessfully() {
        // given
        TodoFindQuery query = TodoFindQuery.builder()
                .id(1L)
                .build();

        // when
        assertThatCode(() ->todoReadUseCase.getTodoById(query))
                .doesNotThrowAnyException();
        FindTodoResult result = todoReadUseCase.getTodoById(query);
        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("투두 id로 조회 not found")
    void getTodoByIdThrowErrorWithNotFound() {
        // given
        TodoFindQuery query = TodoFindQuery
                .builder()
                .id(999999L)
                .build();

        // when & then
        assertThatThrownBy(() -> todoReadUseCase.getTodoById(query))
                .isInstanceOf(TodoAppException.class)
                .hasMessage(TodoMessageType.TODO_NOT_FOUND.getMessage());
    }




}