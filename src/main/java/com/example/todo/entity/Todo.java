package com.example.todo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

import lombok.Data;

@Data
@Entity
@Table(name = "todo")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    @Enumerated(EnumType.STRING)     //Enum型のimportanceフィールドを追加
    private Importance importance;    //Enum型のimportanceフィールドを追加
    private LocalDateTime deadline;
    private boolean completed;
    private LocalDateTime completeAt;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}