package com.beyond.todolist.controller;

import com.beyond.todolist.domain.TodosRepository;
import com.beyond.todolist.dto.TodoStatusRequestDTO;
import com.beyond.todolist.dto.TodosListResponseDTO;
import com.beyond.todolist.dto.TodosSaveRequestDTO;
import com.beyond.todolist.dto.TodosUpdateRequestDTO;
import com.beyond.todolist.service.TodosService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TodosController {

    private final TodosService todosService;

    @GetMapping("")
    @Operation(summary = "TodoList 조회 API")
    public ResponseEntity<List<TodosListResponseDTO>> findList() {
        List<TodosListResponseDTO> lists = todosService.getTodosList();
        return ResponseEntity.ok(lists);
    }

    @PostMapping("")
    @Operation(summary = "TodoList 등록 API")
    public ResponseEntity<TodosSaveRequestDTO> save(@RequestBody TodosSaveRequestDTO todosSaveRequestDTO) {
        todosService.saveTodoList(todosSaveRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/content/{id}")
    @Operation(summary = "TodoList 내용 수정 API")
    public void updateContent(@PathVariable Long id, @RequestBody TodosUpdateRequestDTO todosUpdateRequestDTO) {
        todosService.updateTodoList(id, todosUpdateRequestDTO);
    }

    @PutMapping("/status/{id}")
    @Operation(summary = "TodoList 상태 수정 API")
    public void updateStatus(@PathVariable Long id, @RequestBody TodoStatusRequestDTO todoStatusRequestDTO) {
        todosService.updateStatus(id, todoStatusRequestDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "TodoList 삭제 API")
    public ResponseEntity<Void> deleteTodos(@PathVariable Long id) {
        todosService.deleteTodoList(id);
        return ResponseEntity.noContent().build();
    }
}
