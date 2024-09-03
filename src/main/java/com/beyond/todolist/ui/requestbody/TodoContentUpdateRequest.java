package com.beyond.todolist.ui.requestbody;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TodoContentUpdateRequest {
    @NotEmpty
    private String content;
}
