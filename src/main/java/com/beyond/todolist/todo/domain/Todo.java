package com.beyond.todolist.todo.domain;

import com.beyond.todolist.common.domain.BaseTimeEntity;
import com.beyond.todolist.todo.dto.TodoUpdateRequestDTO;
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

    public void update(TodoUpdateRequestDTO todoUpdateRequestDTO) {
        Optional.ofNullable(todoUpdateRequestDTO.getContent()).ifPresent(content -> this.content = content);
        Optional.ofNullable(todoUpdateRequestDTO.getStatus()).ifPresent(status -> this.status = status);
    }
}
