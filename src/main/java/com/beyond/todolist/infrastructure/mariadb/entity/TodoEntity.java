package com.beyond.todolist.infrastructure.mariadb.entity;

import com.beyond.todolist.application.domain.TodoStatus;
import com.beyond.todolist.application.service.TodoOperationUseCase.TodoCreateCommand;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdDateTime;

    @UpdateTimestamp
    private LocalDateTime updatedDateTime;

    public static TodoEntity createTodo(TodoCreateCommand command) {
        return new TodoEntity(
                null,
                command.getContent(),
                TodoStatus.InProgress,
                null,
                null
        );
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public void updateStatus(TodoStatus status) {
        this.status = status;
    }
}
