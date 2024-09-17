package com.beyond.todolist.repository;

import com.beyond.todolist.domain.Todo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TodoRepository {

  @PersistenceContext
    private EntityManager em;



    public Todo save(Todo todo) {
//        if(todo.getId() == null){
            em.persist(todo); // 새로운 객체 저장
//        } else {
//            em.merge(todo); // 기존 객체 업데이트

        return todo;
    }

//  모든 Todo 객체 조회
    public List<Todo> findAll() {
        return em.createQuery("SELECT t FROM Todo t", Todo.class).getResultList(); // 새로운 리스트로 반환
    }


    // 특정 ID로 Todo 객체를 조회
//    Optional은 값이 있을 수도 있고 없을 수도 있다. null 반환의 위험을 줄여줌.
    public Optional<Todo> findById(int id) {
        Todo todo = em.find(Todo.class, id);
        return Optional.ofNullable(todo);
    }


    public void delete(Todo todo) {
        if (em.contains(todo)) {
            em.remove(todo); // 엔티티가 관리 상태일때 삭제
        } else {
            Todo mergedTodo = em.merge(todo); // 엔티티가 관리하지 않는 상태이면 병합
            em.remove(mergedTodo); // 병합된 엔티티 삭제
        }
    }

}
