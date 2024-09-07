package com.beyond.todolist.dto;

import com.beyond.todolist.domain.Status;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TodosSaveRequestDTO {

    private String content;

    private Status status;

}
