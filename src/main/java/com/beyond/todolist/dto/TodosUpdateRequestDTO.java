package com.beyond.todolist.dto;

import com.beyond.todolist.domain.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TodosUpdateRequestDTO {

    private String content;

}
