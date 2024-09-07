package com.beyond.todolist.service;

import com.beyond.todolist.dto.TodoStatusRequestDTO;
import com.beyond.todolist.dto.TodosListResponseDTO;
import com.beyond.todolist.dto.TodosSaveRequestDTO;
import com.beyond.todolist.dto.TodosUpdateRequestDTO;

import java.util.List;

public interface TodosService {

    List<TodosListResponseDTO> getTodosList();

    void saveTodoList(TodosSaveRequestDTO todosSaveRequestDTO);

    void updateTodoList(Long id, TodosUpdateRequestDTO todosUpdateRequestDTO);

    void updateStatus(Long id, TodoStatusRequestDTO todoStatusRequestDTO);

    void deleteTodoList(Long id);

}
