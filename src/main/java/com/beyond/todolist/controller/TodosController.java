package com.beyond.todolist.controller;

import com.beyond.todolist.domain.Todo;
import com.beyond.todolist.dto.TodoStatusRequestDto;
import com.beyond.todolist.dto.TodosSaveRequestDto;
import com.beyond.todolist.dto.TodosUpdateRequestDto;
import com.beyond.todolist.service.TodosService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodosController {

    private final TodosService todosService;

    @Autowired
    public TodosController(TodosService todosService) {
        this.todosService = todosService;
    }

//    모든 Todo 항목을 조회
    @GetMapping
    @Operation(summary = "모든 Todo 반환", description = "전체 Todo의 목록을 조회한다.")
public ResponseEntity<List<Todo>> getTodos() {
    List<Todo> todos = todosService.getTodos();
    return ResponseEntity.ok(todos); // 200
}

//    특정 ID의 Todo 항목을 반환
    @GetMapping("/{id}")
    @Operation(summary = "해당 ID의 Todo 반환", description = "해당 ID로 Todo를 조회한다.")
    public ResponseEntity<Todo> findTodoById(@PathVariable int id) {
       Todo todo =  todosService.findById(id);
        return ResponseEntity.ok().body(todo); // 200
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "해당 Todo 삭제", description = "ID로 해당 Todo를 삭제한다.")
    public ResponseEntity<Void> deleteTodo(@PathVariable int id) {
        try {
            todosService.deleteById(id);
            return ResponseEntity.noContent().build(); // 204
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404
        }
    }

    //    새로운 Todo 항목 추가
    @PostMapping
    @Operation(summary = "새로운 Todo 생성", description = "새로운 Todo를 추가한다.")
    public ResponseEntity<Todo> addTodo(@RequestBody TodosSaveRequestDto todosSaveRequestDto) {
        Todo todo = todosService.save(todosSaveRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(todo); // 201
    }

    // Todo 항목을 수정
    @PostMapping("/{id}")
    @Operation(summary = "Todo 수정", description = "해당 Todo를 수정한다.")
    public ResponseEntity<Todo> updateTodo(
            @PathVariable int id,
            @RequestBody TodosUpdateRequestDto todosUpdateRequestDto) {

        Todo updatedTodo = todosService.updateTodo(id, todosUpdateRequestDto);
        // 저장된 Todo 객체 반환
        return ResponseEntity.ok().body(updatedTodo);
    }

    // status를 Finished로 변경
    @PatchMapping("/status/{id}")
    @Operation(summary = "Finsh/INPROGRESS")
    public ResponseEntity<Todo> finishTodo(@PathVariable int id, @RequestBody TodoStatusRequestDto todoStatusRequestDto) {
        Todo updatedTodo = todosService.updateStatus(id, todoStatusRequestDto);
        return ResponseEntity.ok().body(updatedTodo); // 상태 업데이트
    }


}
