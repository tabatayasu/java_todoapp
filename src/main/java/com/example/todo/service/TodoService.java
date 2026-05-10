package com.example.todo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todo.dao.TodoRepository;
import com.example.todo.dto.TodoDto;
import com.example.todo.entity.Todo;

@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;

    public List<Todo> findAll() {
        return repository.findAll();
    }

    public void create(TodoDto dto) {
        Todo todo = new Todo();
        todo.setTitle(dto.getTitle());
        todo.setDescription(dto.getDescription());
        todo.setImportance(dto.getImportance());
        todo.setDeadline(dto.getDeadline());
        todo.setCreateAt(LocalDateTime.now());
        repository.save(todo);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    //編集機能メソッド
    public Todo findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void update(Long id, TodoDto dto) {
        Todo todo = repository.findById(id).orElse(null);
        if (todo != null) {
            todo.setTitle(dto.getTitle());
            todo.setDescription(dto.getDescription());
            todo.setImportance(dto.getImportance());
            todo.setDeadline(dto.getDeadline());
            todo.setUpdateAt(LocalDateTime.now());
            repository.save(todo);
        }
    }
    //編集機能メソッド//
    
    //サマリー機能メソッド
    public long getTotalCount() {
        return repository.count();
    }

    public long getIncompleteCount() {
        return repository.findAll().stream()
            .filter(todo -> todo.getCompleteAt() == null)
            .count();
    }

    public long getOverdueCount() {
        LocalDateTime now = LocalDateTime.now();
        return repository.findAll().stream()
            .filter(todo -> todo.getCompleteAt() == null && todo.getDeadline() != null && todo.getDeadline().isBefore(now))
            .count();
    }

    public long getDueSoonCount() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime threeDaysLater = now.plusDays(3);
        return repository.findAll().stream()
            .filter(todo -> todo.getCompleteAt() == null && todo.getDeadline() != null && todo.getDeadline().isBefore(threeDaysLater))
            .count();
    }

    public void toggleComplete(Long id) {
        Todo todo = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Not found: " + id));

        boolean nowCompleted = !todo.isCompleted();
        todo.setCompleted(nowCompleted);

        if (nowCompleted) {
            todo.setCompleteAt(LocalDateTime.now());
        } else {
            todo.setCompleteAt(null);
        }

        repository.save(todo);
    }

    public List<Todo> findIncomplete() {
        return repository.findAll().stream()
            .filter(todo -> todo.getCompleteAt() == null)
            .toList();
    }

    // 完了したTODOを取得
    public List<Todo> findCompleted() {
        return repository.findAll().stream()
            .filter(todo -> todo.getCompleteAt() != null)
            .toList();
    }
}