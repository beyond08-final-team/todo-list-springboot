package com.beyond.todolist.todo.dto;

import com.beyond.todolist.todo.domain.TodoStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public class TodoUpdateRequestDTO {

    private String content;

    private TodoStatus status;
}
