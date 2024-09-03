package com.beyond.todolist.infrastructure.mariadb.entity;

import com.beyond.todolist.application.domain.TodoStatus;
import com.beyond.todolist.application.service.TodoOperationUseCase;
import com.beyond.todolist.application.service.TodoOperationUseCase.TodoCreateCommand;
import com.beyond.todolist.application.service.TodoOperationUseCase.TodoStatusUpdateCommand;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "todos")
public class TodoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    @Enumerated(EnumType.STRING)
    private TodoStatus status;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;

    public static TodoEntity createTodo(TodoCreateCommand command) {
        LocalDateTime now = LocalDateTime.now();
        return new TodoEntity(
                null,
                command.getContent(),
                TodoStatus.InProgress,
                now,
                now
        );
    }

    public void updateContent(String content) {
        this.content = content;
        this.updatedDateTime = LocalDateTime.now();
    }

    public void updateStatus(TodoStatus status) {
        this.status = status;
        this.updatedDateTime = LocalDateTime.now();
    }
}
