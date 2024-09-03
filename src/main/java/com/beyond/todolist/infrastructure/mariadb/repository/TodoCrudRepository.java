package com.beyond.todolist.infrastructure.mariadb.repository;

import com.beyond.todolist.infrastructure.mariadb.entity.TodoEntity;
import org.springframework.data.repository.CrudRepository;

public interface TodoCrudRepository extends CrudRepository<TodoEntity, Long> {
}
