package com.neosoft.neosoftToDo.Service;

import com.neosoft.neosoftToDo.Entity.TodoDTO;
import com.neosoft.neosoftToDo.Entity.TodoEntity;
import com.neosoft.neosoftToDo.Exception.neoSoftNotFoundException;
import com.neosoft.neosoftToDo.Repository.NeoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToService {

    private final NeoRepository todoRepository;

    @Autowired
    public ToService(NeoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }



    public List<TodoDTO> getAllTodos() {
        return todoRepository.findAll().stream()
                .map(this::convertToDTO)
                .toList();
    }

    public Optional<TodoDTO> getTodoById(Long id) {
        return Optional.ofNullable(convertToDTO(todoRepository.findById(id)
                        .orElseThrow(() -> new neoSoftNotFoundException("Todo not found with id: " + id)))
        );
    }

    public TodoDTO createTodo(TodoDTO todoDTO) {
        TodoEntity entity = convertToEntity(todoDTO);
        return convertToDTO(todoRepository.save(entity));
    }

    public TodoDTO updateTodo(Long id, TodoDTO todoDTO) {
        TodoEntity todo = todoRepository.findById(id)
                .orElseThrow(() -> new neoSoftNotFoundException("Todo not found with id: " + id));

        todo.setTitle(todoDTO.getTitle());
        todo.setCompleted(todoDTO.isCompleted());

        return convertToDTO(todoRepository.save(todo));
    }
    private TodoDTO convertToDTO(TodoEntity entity) {
        return new TodoDTO(entity.getId(), entity.getTitle(), entity.isCompleted());
    }

    private TodoEntity convertToEntity(TodoDTO dto) {
        TodoEntity entity = new TodoEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setCompleted(dto.isCompleted());
        return entity;
    }

}