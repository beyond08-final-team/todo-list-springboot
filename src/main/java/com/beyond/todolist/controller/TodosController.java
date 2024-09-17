package com.beyond.todolist.controller;

import com.beyond.todolist.domain.Todos;
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
@CrossOrigin(origins = "http://localhost:5173")
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
        TodosSaveRequestDTO savedTask = todosService.saveTodoList(todosSaveRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
    }

    @PatchMapping("/content/{id}")
    @Operation(summary = "TodoList 내용 수정 API")
    public ResponseEntity<Todos> updateContent(
            @PathVariable Long id,
            @RequestBody TodosUpdateRequestDTO todosUpdateRequestDTO
    ) {
        Todos updatedTodo = todosService.updateTodoList(id, todosUpdateRequestDTO);
        return ResponseEntity.ok(updatedTodo); // Return the updated Todo object
    }

    @PatchMapping("/statusDone/{id}")
    @Operation(summary = "TodoList 상태 DONE 수정 API")
    public ResponseEntity<Todos> updateStatusDone(@PathVariable Long id) {
        Todos updatedTodo = todosService.updateStatusDone(id);
        return ResponseEntity.ok(updatedTodo); // 수정된 작업 데이터 반환
    }

    @PatchMapping("/statusInProgress/{id}")
    @Operation(summary = "TodoList 상태 InProgress 수정 API")
    public ResponseEntity<Todos> updateStatusInProgress(@PathVariable Long id) {
        Todos todos = todosService.updateStatusInProgress(id);
        return ResponseEntity.ok(todos);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "TodoList 삭제 API")
    public ResponseEntity<Void> deleteTodos(@PathVariable Long id) {
        todosService.deleteTodoList(id);
        return ResponseEntity.noContent().build();
    }
}
