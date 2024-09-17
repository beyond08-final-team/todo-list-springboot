package com.beyond.todolist.service;

import com.beyond.todolist.domain.Todos;
import com.beyond.todolist.dto.TodosListResponseDTO;
import com.beyond.todolist.dto.TodosSaveRequestDTO;
import com.beyond.todolist.dto.TodosUpdateRequestDTO;

import java.util.List;

public interface TodosService {

    List<TodosListResponseDTO> getTodosList();

    TodosSaveRequestDTO saveTodoList(TodosSaveRequestDTO todosSaveRequestDTO);

    Todos updateTodoList(Long id, TodosUpdateRequestDTO todosUpdateRequestDTO);

    Todos updateStatusDone(Long id);

    Todos updateStatusInProgress(Long id);

    void deleteTodoList(Long id);
}
