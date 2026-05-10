package com.example.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.todo.dto.TodoDto;
import com.example.todo.entity.Todo;
import com.example.todo.service.TodoService;
import java.util.List;

@Controller
public class TodoController {

    @Autowired
    private TodoService service;

    @GetMapping("/")
    public String index(Model model) {
        List<Todo> todos = service.findIncomplete();
        model.addAttribute("todos", todos);
        model.addAttribute("totalCount", service.getTotalCount());
        model.addAttribute("incompleteCount", service.getIncompleteCount());
        model.addAttribute("overdueCount", service.getOverdueCount());
        model.addAttribute("dueSoonCount", service.getDueSoonCount());
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
        Todo todo = service.findById(id);
        if (todo == null) {
            return "redirect:/";
        }
        model.addAttribute("todo", todo);
        return "edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, TodoDto dto) {
        service.update(id, dto);
        return"redirect:/";
    }

    //完了/未完了の切り替え
    @GetMapping("/toggle/{id}")
    public String toggleComplete(@PathVariable Long id) {
        service.toggleComplete(id);
        return "redirect:/";
    }

    //完了/未完了の切り替え//
    @GetMapping("/history")
    public String history(Model model) {
        List<Todo> completedTodos = service.findCompleted();
        model.addAttribute("completedTodos", completedTodos);
        return "history";  // 新しいビュー
    }

    @GetMapping("/api/history")
    @ResponseBody
    public List<TodoDto> getHistoryJson() {
        List<Todo> completedTodos = service.findCompleted();
        return completedTodos.stream().map(todo -> {
            TodoDto dto = new TodoDto();
            dto.setTitle(todo.getTitle());
            dto.setDescription(todo.getDescription());
            dto.setImportance(todo.getImportance());
            dto.setDeadline(todo.getDeadline());
            return dto;
        }).toList();
    }

    @GetMapping("/api/todo/{id}")
    @ResponseBody
    public TodoDto getTodo(@PathVariable Long id) {
        Todo todo = service.findById(id);
        TodoDto dto = new TodoDto();
        dto.setTitle(todo.getTitle());
        dto.setDescription(todo.getDescription());
        dto.setImportance(todo.getImportance());
        dto.setDeadline(todo.getDeadline());
        return dto;
    }
}