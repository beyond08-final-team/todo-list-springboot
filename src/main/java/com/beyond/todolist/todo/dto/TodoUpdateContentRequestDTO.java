package com.beyond.todolist.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public class TodoUpdateContentRequestDTO {

    private String content;
}
