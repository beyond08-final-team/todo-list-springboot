package com.beyond.todolist.service;

import com.beyond.todolist.domain.Todo;
import com.beyond.todolist.dto.TodoStatusRequestDto;
import com.beyond.todolist.dto.TodosSaveRequestDto;
import com.beyond.todolist.dto.TodosUpdateRequestDto;
import com.beyond.todolist.exceptions.TodoException;
import com.beyond.todolist.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // 조회하는 곳에서 성능 업 (읽기 전용)
@RequiredArgsConstructor
public class TodosService {

//    이제 변경할 일이 없기 때문에 final을 붙여줌
    private final TodoRepository todoRepository;

//    한 번 더 방어/ 테스트 케이스 작성 시 좋음.
//    @Autowired
//    public TodosService(TodoRepository todoRepository) {
//        this.todoRepository = todoRepository;
//    }


    @Transactional
    public Todo save(TodosSaveRequestDto todosSaveRequestDto) {

        Todo todo = new Todo(
                todosSaveRequestDto.getContent(),
                todosSaveRequestDto.getStatus()
        );

        return todoRepository.save(todo);
    }

//    모든 Todo 항목 조회
    @Transactional // 데이터 변경이 일어나는 메서드에 readOnly 제거
    public List<Todo>  getTodos(){
        return todoRepository.findAll();
    }


// ID로 Todo 항목 조회
    public Todo findById(int id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new TodoException("해당하는 ID를 찾지 못하였습니다." + id));
    }
// Todo 항목 삭제
    @Transactional
    public void deleteById(int id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoException("해당하는 ID를 찾지 못하였습니다." + id));
        todoRepository.delete(todo); // Todo 삭제
    }

    @Transactional
    public Todo updateTodo(int id, TodosUpdateRequestDto todosUpdateRequestDto) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoException("해당하는 ID를 찿지 못하였습니다." +id));
        todo.setContent(todosUpdateRequestDto.getContent()); // content 업데이트
        todoRepository.save(todo);
        return todo;
    }

    @Transactional
    public Todo updateStatus(int id, TodoStatusRequestDto todoStatusRequestDto) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoException("해당하는 ID를 찿지 못하였습니다."+id));

        todo.updateStatus(todoStatusRequestDto.getStatus());
        return todoRepository.save(todo);
    }
}
