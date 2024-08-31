package com.beyond.todolist.todo.dto;

import com.beyond.todolist.todo.domain.TodoStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = lombok.AccessLevel.PUBLIC)
@AllArgsConstructor
@Getter
public class TodoRegisterRequestDTO {

    @NotNull
    private String content;

    @NotNull
    private TodoStatus status;
}
