package com.beyond.todolist.dto;

import com.beyond.todolist.domain.Status;
import lombok.Data;

@Data
public class TodoStatusRequestDto {

    private Status status;
}
