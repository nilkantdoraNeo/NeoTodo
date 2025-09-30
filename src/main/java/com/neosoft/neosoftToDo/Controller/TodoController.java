package com.neosoft.neosoftToDo.Controller;
import com.neosoft.neosoftToDo.Entity.TodoDTO;
import com.neosoft.neosoftToDo.Entity.TodoEntity;
import com.neosoft.neosoftToDo.Service.ToService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/neoTodo")
public class TodoController {
    private static final Logger log = LoggerFactory.getLogger(TodoController.class);
    private final ToService todoService;


    public TodoController(ToService todoService) {
        this.todoService = todoService;
    }



    @GetMapping
    public List<TodoDTO> getAllTodos() {
        log.info("Fetching all todos");
        return todoService.getAllTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoDTO> getTodoById(@PathVariable Long id) {
        log.info("Fetching todo with ID: {}", id);
        return todoService.getTodoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public TodoDTO createTodo(@RequestBody TodoDTO todoDTO) {
        log.info("Creating new todo: {}", todoDTO.getTitle());
        return todoService.createTodo(todoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoDTO> updateTodo(@PathVariable Long id, @RequestBody TodoDTO todoDTO) {
        log.info("Updating todo with ID: {}", id);
        return ResponseEntity.ok(todoService.updateTodo(id, todoDTO));
    }
}