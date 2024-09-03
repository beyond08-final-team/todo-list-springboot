package com.beyond.todolist.ui.requestbody;

import com.beyond.todolist.application.domain.TodoStatus;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TodoStatusUpdateRequest {

    @NotNull
    TodoStatus status;
}
