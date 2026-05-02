package com.example.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.todo.dto.TodoDto;
import com.example.todo.service.TodoService;

@Controller
public class TodoController {

    @Autowired
    private TodoService service;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("todos", service.findAll());
        return "index";
    }

    @PostMapping("/add")
    public String add(TodoDto dto) {
        service.create(dto);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/";
    }

    //編集画面表示と更新処理のエンドポイント
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("todo",service.findById(id));
        return "edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, TodoDto dto) {
        service.update(id, dto);
        return"redirect:/";
    }
    //編集画面表示と更新処理のエンドポイント//
}