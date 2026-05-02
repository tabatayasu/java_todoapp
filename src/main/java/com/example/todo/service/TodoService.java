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
}