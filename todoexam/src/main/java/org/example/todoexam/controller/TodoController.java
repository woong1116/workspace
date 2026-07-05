package org.example.todoexam.controller;

import lombok.RequiredArgsConstructor;
import org.example.todoexam.dto.TodoDto;
import org.example.todoexam.entity.Todo;
import org.example.todoexam.service.TodoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public Todo create(@RequestBody TodoDto dto) {
        return todoService.create(dto);
    }

    @GetMapping
    public List<Todo> findAll() {
        return todoService.findAll();
    }

    @GetMapping("/{id}")
    public Todo findById(@PathVariable Long id) {
        return todoService.findById(id);
    }

    @PutMapping("/{id}")
    public Todo update(@PathVariable Long id,
                       @RequestBody TodoDto dto) {
        return todoService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        todoService.delete(id);
        return "삭제";
    }
}