package com.beyond.todolist.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Data
@Entity
public class Todo {

    @Id
    @GeneratedValue //    자동으로 1씩 늘어나게 해줌
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GeneratedValue(strategy = GenerationType.IDENTITY) 자동으로 다르게 인식
    private int id;

    @NotNull
    @Column(length = 20)
    private String content;

//    EnumType.ORDINAL : enum 순서를 데이터베이스에 저장
//    EnumType.STRING : enum 이름을 데이터베이스에 저장
    @Enumerated(EnumType.STRING)
    @ColumnDefault("InProgress")
    private Status status;

//    @Column(name = "create_date_time")
//    private LocalDateTime create_at;
//
//    @Column(name = "update_date_time")
//    private LocalDateTime update_at;

    public Todo() {

    }


// 생성자
    public Todo(String content, Status status) {
        this.content = content;
        this.status = status;
    }

//
//    //    자동으로 시간 설정함.
//    @PrePersist
//    public void onPrePersist() {
//        this.create_at = LocalDateTime.now();
//    }
//
//    @PreUpdate
//    public void onPreUpdate() {
//        this.update_at = LocalDateTime.now();
//    }
//

    public void updateStatus(Status status) {

        this.status = status;
    }


}
