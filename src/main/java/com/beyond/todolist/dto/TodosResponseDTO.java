package com.beyond.todolist.dto;

import com.beyond.todolist.domain.Status;
import com.beyond.todolist.domain.Todos;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TodosResponseDTO {

    private Long id;

    private String content;

    private Status status;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    public static TodosResponseDTO of(Todos todos) {
        return TodosResponseDTO.builder()
                .id(todos.getId())
                .content(todos.getContent())
                .status(todos.getStatus())
                .build();
    }
}
