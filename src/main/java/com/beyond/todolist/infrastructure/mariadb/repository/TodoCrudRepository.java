package com.beyond.todolist.infrastructure.mariadb.repository;

import com.beyond.todolist.infrastructure.mariadb.entity.TodoEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TodoCrudRepository extends CrudRepository<TodoEntity, Long> {

    List<TodoEntity> findAll();
}
