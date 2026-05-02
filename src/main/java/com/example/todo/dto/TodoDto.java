package com.example.todo.dto;

import java.time.LocalDateTime;
import com.example.todo.entity.Importance;  //Enum型のImportanceクラスをインポート

import lombok.Data;

@Data
public class TodoDto {
    private String title;
    private String description;
    private Importance importance;   //Enum型のimportanceフィールドを追加?
    private LocalDateTime deadline;
}