package com.beyond.todolist.todo.dto;

import com.beyond.todolist.todo.entity.TodoStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public class CreateTodoReq {

    @NotBlank(message = "공백만 입력될 수 없습니다.")
    private String content;

    @NotNull
    private TodoStatus status;
}
