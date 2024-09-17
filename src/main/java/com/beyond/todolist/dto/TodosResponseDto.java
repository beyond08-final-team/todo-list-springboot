package com.beyond.todolist.dto;

import com.beyond.todolist.domain.Status;
import com.beyond.todolist.domain.Todo;
import lombok.Data;
import lombok.Getter;

@Data
public class TodosResponseDto {

    private int id;

    private String content;

    private Status status;

    private Long createdAt;

    private Long updatedAt;

    public TodosResponseDto of(Todo todo) {
        TodosResponseDto dto = new TodosResponseDto();
        dto.setId(todo.getId());
        dto.setContent(todo.getContent());
        dto.setStatus(todo.getStatus());
        return dto;
    }

}
