package com.beyond.todolist.dto;

import com.beyond.todolist.domain.Status;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TodosListResponseDTO {

    private Long id;

    private String content;

    private Status status;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;
}
