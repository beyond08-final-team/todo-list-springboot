package com.beyond.todolist.todo.controller;

import com.beyond.todolist.todo.domain.Todo;
import com.beyond.todolist.todo.dto.TodoRegisterRequestDTO;
import com.beyond.todolist.todo.dto.TodoUpdateContentRequestDTO;
import com.beyond.todolist.todo.dto.TodoUpdateStatusRequestDTO;
import com.beyond.todolist.todo.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "TodoApp", description = "투두앱 만들기")
@RestController
@RequestMapping("/api/v1/todos")
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class TodoController {

    private final TodoService todoService;

    @Operation(summary = "할 일 리스트를 생성하는 API")
    @PostMapping("")
    public ResponseEntity<Void> registerTodo(@RequestBody @Valid TodoRegisterRequestDTO todoRegisterRequestDTO) {

        todoService.registerTodo(todoRegisterRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "할 일 리스트를 반환하는 API")
    @GetMapping("")
    public ResponseEntity<List<Todo>> getAllTodos() {

        List<Todo> todos = todoService.getAllTodos();
        return ResponseEntity.ok(todos);
    }

    @Operation(summary = "할 일 리스트의 내용을 수정하는 API")
    @PatchMapping("/{id}/content")
    public ResponseEntity<Void> updateContent(@PathVariable("id") Long id,
                                           @RequestBody @Valid TodoUpdateContentRequestDTO todoUpdateContentRequestDTO) {

        todoService.updateContent(id, todoUpdateContentRequestDTO);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "할 일 리스트의 상태를 수정하는 API")
    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateStatus(@PathVariable("id") Long id,
                                              @RequestBody @Valid TodoUpdateStatusRequestDTO todoUpdateStatusRequestDTO) {

        todoService.updateStatus(id, todoUpdateStatusRequestDTO);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "할 일 리스트를 삭제하는 API")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable("id") Long id) {

        todoService.deleteTodo(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
