package com.beyond.todolist.model;

import jakarta.persistence.Entity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "todos")
@Data // Lombok의 @Data 어노테이션을 사용하면 모든 getter, setter, toString 등이 자동 생성됩니다.
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String status; // 상태 필드 추가

    @Column(nullable = false)
    private String createdAt; // 생성 시간 필드 추가
}
