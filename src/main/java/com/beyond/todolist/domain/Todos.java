package com.beyond.todolist.domain;

import com.beyond.todolist.common.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Data
@Entity
@Builder
@NoArgsConstructor
@Table(name = "Todos")
public class Todos extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(length = 24)
    private String content;

    @NotNull
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'InProgress'")
    private Status status;

    public Todos(Long id, String content, Status status) {
        this.id = id;
        this.content = content;
        this.status = status;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public void updateStatus(Status status) {
        this.status = status;
    }

    public void updateStatusDone() {
        this.status = Status.Done;
    }

    public void updateStatusInProgress() {
        this.status = Status.InProgress;
    }
}
