package org.example.todoexam.service;

import lombok.RequiredArgsConstructor;
import org.example.todoexam.dto.TodoDto;
import org.example.todoexam.entity.Todo;
import org.example.todoexam.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public Todo create(TodoDto dto) {

        Todo todo = new Todo();
        todo.setTitle(dto.getTitle());
        todo.setContent(dto.getContent());

        return todoRepository.save(todo);
    }

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    public Todo findById(Long id) {
        return todoRepository.findById(id).orElse(null);
    }

    public Todo update(Long id, TodoDto dto) {

        Todo todo = todoRepository.findById(id)
                .orElseThrow();

        todo.setTitle(dto.getTitle());
        todo.setContent(dto.getContent());

        return todoRepository.save(todo);
    }

    public void delete(Long id) {
        todoRepository.deleteById(id);
    }
}