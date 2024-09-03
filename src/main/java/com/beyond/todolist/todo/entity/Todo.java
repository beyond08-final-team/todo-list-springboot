package com.beyond.todolist.todo.entity;

import com.beyond.todolist.common.entity.BaseTimeEntity;
import com.beyond.todolist.todo.dto.TodoReq;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Entity
@Getter
@Table(name = "todos") // DB table 이름이 todos
public class Todo extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TodoStatus status;

    // update method
    @Builder
    public Todo(String content, TodoStatus status){
        this.content = content;
        this.status = status;
    }

    public void todoUpdate(TodoReq todoReq) {
        this.content = todoReq.getContent();
        this.status = todoReq.getStatus();
    }
}
