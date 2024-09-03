package com.beyond.todolist.ui.controller;

import com.beyond.todolist.application.service.TodoOperationUseCase;
import com.beyond.todolist.application.service.TodoOperationUseCase.TodoCreateCommand;
import com.beyond.todolist.application.service.TodoReadUseCase;
import com.beyond.todolist.ui.requestbody.TodoContentUpdateRequest;
import com.beyond.todolist.ui.requestbody.TodoCreateRequest;
import com.beyond.todolist.ui.requestbody.TodoStatusUpdateRequest;
import com.beyond.todolist.ui.view.ApiResponseView;
import com.beyond.todolist.ui.view.TodoView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.beyond.todolist.application.service.TodoOperationUseCase.*;
import static com.beyond.todolist.application.service.TodoReadUseCase.*;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TodoController {

    private final TodoOperationUseCase todoOperationUseCase;

    private final TodoReadUseCase todoReadUseCase;


    @PostMapping("/todo")
    public ResponseEntity<ApiResponseView<TodoView>> createTodo(@RequestBody @Validated TodoCreateRequest request) {

        TodoCreateCommand command = TodoCreateCommand.builder()
                .content(request.getContent())
                .build();

        FindTodoResult result = todoOperationUseCase.saveTodo(command);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponseView<>(new TodoView(result)));
    }

    @GetMapping("/todos")
    public ResponseEntity<ApiResponseView<List<TodoView>>> getTodos() {

        TodoFindQuery query = TodoFindQuery.builder()
                .build();

        List<FindTodoResult> results = todoReadUseCase.getTodos(query);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponseView<>(results.stream().map(TodoView::new).toList()));
    }

    @PatchMapping("/todo/{id}/content")
    public ResponseEntity<ApiResponseView<TodoView>> updateContent(@PathVariable Long id, @RequestBody @Validated TodoContentUpdateRequest request) {

        TodoContentUpdateCommand command = TodoContentUpdateCommand.builder()
                .id(id)
                .content(request.getContent())
                .build();

        FindTodoResult result = todoOperationUseCase.updateTodoContent(command);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponseView<>(new TodoView(result)));
    }

    @PatchMapping("/todo/{id}/status")
    public ResponseEntity<ApiResponseView<TodoView>> updateStatus(@PathVariable Long id, @RequestBody @Validated TodoStatusUpdateRequest request) {

        TodoStatusUpdateCommand command = TodoStatusUpdateCommand.builder()
                .id(id)
                .status(request.getStatus())
                .build();

        FindTodoResult result = todoOperationUseCase.updateTodoStatus(command);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponseView<>(new TodoView(result)));
    }

    @DeleteMapping("/todo/{id}")
    public ResponseEntity<ApiResponseView<TodoView>> updateStatus(@PathVariable Long id) {

        todoOperationUseCase.deleteTodo(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
