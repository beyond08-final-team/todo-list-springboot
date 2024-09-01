package com.beyond.todolist.todo.dto;

import com.beyond.todolist.todo.entity.TodoStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public class CreateTodoReq {

    @NotNull
    private String content;

    @NotNull
    private TodoStatus status;
}
