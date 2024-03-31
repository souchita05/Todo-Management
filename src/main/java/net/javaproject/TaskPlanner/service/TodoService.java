package net.javaproject.TaskPlanner.service;

import jakarta.persistence.Id;
import net.javaproject.TaskPlanner.dto.TodoDto;

import java.util.List;

public interface TodoService {
    TodoDto addTodo(TodoDto TodoDto);
    TodoDto getTodo(Long id);
    List<TodoDto> GetAllTodos();
    TodoDto UpdateTodo(TodoDto TodoDto, Long id);
    void deleteTodo(Long id);
    TodoDto completeTodo(Long id);
    TodoDto incompleteTodo(Long id);
}