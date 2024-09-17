package com.beyond.todolist.dto;

import com.beyond.todolist.domain.Status;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class TodosSaveRequestDTO {

    private String content;

}
