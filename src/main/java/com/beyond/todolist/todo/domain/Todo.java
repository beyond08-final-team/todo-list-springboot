package com.beyond.todolist.todo.domain;

import com.beyond.todolist.common.domain.BaseTimeEntity;
import com.beyond.todolist.todo.dto.TodoUpdateContentRequestDTO;
import com.beyond.todolist.todo.dto.TodoUpdateStatusRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Builder
public class Todo extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TodoStatus status;

    public void updateContent(TodoUpdateContentRequestDTO todoUpdateContentRequestDTO) {
        Optional.ofNullable(todoUpdateContentRequestDTO.getContent()).ifPresent(content -> this.content = content);
    }

    public void updateStatus(TodoUpdateStatusRequestDTO todoUpdateStatusRequestDTO) {
        Optional.ofNullable(todoUpdateStatusRequestDTO.getStatus()).ifPresent(status -> this.status = status);
    }
}
