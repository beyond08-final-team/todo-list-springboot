package com.beyond.todolist.service;

import com.beyond.todolist.domain.Todos;
import com.beyond.todolist.domain.TodosRepository;
import com.beyond.todolist.dto.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Builder
@RequiredArgsConstructor
public class TodosServiceImpl implements TodosService {

    private final TodosRepository todosRepository;

    @Override
    @Transactional
    public List<TodosListResponseDTO> getTodosList() {

        List<Todos> todosList = todosRepository.findAll();

        return todosList.stream()
                .map(todos -> TodosListResponseDTO.builder()
                        .id(todos.getId())
                        .content(todos.getContent())
                        .status(todos.getStatus())
                        .createdDate(todos.getCreatedDate())
                        .updatedDate(todos.getUpdatedDate())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TodosSaveRequestDTO saveTodoList(TodosSaveRequestDTO todosSaveRequestDTO) {

        Todos todos = Todos.builder()
                .content(todosSaveRequestDTO.getContent())
                .build();

        todos = todosRepository.save(todos);

        TodosResponseDTO.of(todos);
        return todosSaveRequestDTO;
    }

    @Override
    @Transactional
    public Todos updateTodoList(Long id, TodosUpdateRequestDTO todosUpdateRequestDTO) {

        Todos todos = todosRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 ID를 찾을 수 없습니다."));

        todos.updateContent(todosUpdateRequestDTO.getContent());
        return todos;
    }

    @Override
    @Transactional
    public Todos updateStatusDone(Long id) {
        Todos todos = todosRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 ID를 찾을 수 없습니다."));

        todos.updateStatusDone();
        todosRepository.save(todos);
        return todos; // 수정된 Todos 객체 반환
    }


    @Override
    @Transactional
    public Todos updateStatusInProgress(Long id) {
        Todos todos = todosRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 ID를 찾을 수 없습니다."));

        todos.updateStatusInProgress();
        todosRepository.save(todos);
        return todos;
    }

    @Override
    @Transactional
    public void deleteTodoList(Long id) {

        Todos todos = todosRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 ID를 찾을 수 없습니다."));

        todosRepository.delete(todos);
    }

}
