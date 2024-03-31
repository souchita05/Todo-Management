package net.javaproject.TaskPlanner.service.impl;

import lombok.AllArgsConstructor;
import net.javaproject.TaskPlanner.Exception.ResourceNotFoundException;
import net.javaproject.TaskPlanner.dto.TodoDto;
import net.javaproject.TaskPlanner.entity.Todo;
import net.javaproject.TaskPlanner.repository.TodoRepository;
import net.javaproject.TaskPlanner.service.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class TodoServiceImpl implements TodoService {
    private TodoRepository todoRepository;
    private ModelMapper modelMapper;
    @Override
    public TodoDto addTodo(TodoDto TodoDto){
        //convert TodoDto into Todo jpa Entity
      /*  Todo todo = new Todo();
        todo.setTitle(TodoDto.getTitle());
        todo.setDescription(TodoDto.getDescription());
        todo.setCompleted(TodoDto.isCompleted()); */

        Todo todo=modelMapper.map(TodoDto, Todo.class);

        //Todo jpa entity
        Todo savedtodo =todoRepository.save(todo);
        //convert savedtodo JPA entity object to tododto;

    /*    TodoDto SavedTodoDTO= new TodoDto();
        SavedTodoDTO.setId(savedtodo.getId());
        SavedTodoDTO.setTitle(savedtodo.getTitle());
        SavedTodoDTO.setDescription(savedtodo.getDescription());
        SavedTodoDTO.setCompleted(savedtodo.isCompleted()); */

        TodoDto SavedTodoDTO=modelMapper.map(savedtodo, TodoDto.class);
        return SavedTodoDTO;

    }
    @Override
    public TodoDto getTodo(Long id){
        Todo todo=todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("To DO not found with Id : "+id));
        return modelMapper.map(todo, TodoDto.class);
    }

    @Override
    public List<TodoDto> GetAllTodos() {

        List<Todo> todos= todoRepository.findAll();

        return todos.stream().map((todo) ->modelMapper.map(todo, TodoDto.class)).collect(Collectors.toList());
    }

    @Override
    public TodoDto UpdateTodo(TodoDto TodoDto, Long id) {
        Todo todo=todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id "+ id));
        todo.setTitle(TodoDto.getTitle());
        todo.setDescription(TodoDto.getDescription());
        todo.setCompleted(TodoDto.isCompleted());

        Todo updatedTodo=todoRepository.save(todo);

        return modelMapper.map(updatedTodo, TodoDto.class);

    }

    @Override
    public void deleteTodo(Long id) {
        Todo todo=todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id "+ id));

        todoRepository.deleteById(id);
    }

    @Override
    public TodoDto completeTodo(Long id) {
        Todo todo=todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id "+ id));
        todo.setCompleted(Boolean.TRUE);
        Todo updatedTodo=todoRepository.save(todo);
        return modelMapper.map(updatedTodo, TodoDto.class);
    }

    @Override
    public TodoDto incompleteTodo(Long id) {
        Todo todo=todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id "+ id));
        todo.setCompleted(Boolean.FALSE);
        Todo updatedTodo=todoRepository.save(todo);
        return modelMapper.map(updatedTodo, TodoDto.class);
    }
}
