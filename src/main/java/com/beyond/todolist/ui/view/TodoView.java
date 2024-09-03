package com.beyond.todolist.ui.view;

import com.beyond.todolist.application.domain.TodoStatus;
import com.beyond.todolist.application.service.TodoReadUseCase.FindTodoResult;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TodoView {

    private final Long id;
    private final String content;
    private final TodoStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private final LocalDateTime createdDateTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private final LocalDateTime updatedDateTime;

    public TodoView(FindTodoResult todo) {
        this.id = todo.getId();
        this.content = todo.getContent();
        this.status = todo.getStatus();
        this.createdDateTime = todo.getCreatedDateTime();
        this.updatedDateTime = todo.getUpdatedDateTime();
    }

}
