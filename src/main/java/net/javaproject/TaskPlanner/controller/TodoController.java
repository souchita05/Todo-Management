package net.javaproject.TaskPlanner.controller;

import lombok.AllArgsConstructor;
import net.javaproject.TaskPlanner.dto.TodoDto;
import net.javaproject.TaskPlanner.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/Todos")
@AllArgsConstructor
public class TodoController {
    private TodoService todoService;
    //build add todo api
    @PostMapping
    public ResponseEntity<TodoDto>  addTodo(@RequestBody TodoDto todoDto){
        TodoDto savedtodo= todoService.addTodo(todoDto);
        return new ResponseEntity<>(savedtodo, HttpStatus.CREATED);
    }

    //build get todo rest api
    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable("id") Long todoId){
       TodoDto todoDto= todoService.getTodo(todoId);
       return new ResponseEntity<>(todoDto, HttpStatus.OK);
    }

    //build get all todo rest api
    @GetMapping
    public ResponseEntity<List<TodoDto>> GetAllTodos(){
        List<TodoDto> todos= todoService.GetAllTodos();
    //return new ResponseEntity<>(todos, HttpStatus.OK);
    return ResponseEntity.ok(todos);
    }

    //build update todo api
    @PutMapping("{id}")
    public ResponseEntity<TodoDto>  UpdateTodo(@RequestBody TodoDto todoDto,@PathVariable("id") Long todoId){
        TodoDto updatedTodo = todoService.UpdateTodo(todoDto,todoId);
        return ResponseEntity.ok(updatedTodo);
    }

    //build delete todo api
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long todoId){
        todoService.deleteTodo(todoId);
        return ResponseEntity.ok("Todo Deleted Successfully!");

    }

    //build complete todo api
    @PatchMapping("{id}/complete")
    public ResponseEntity<TodoDto>  completeTodo(@PathVariable("id") Long todoId){
        TodoDto updatedTodo = todoService.completeTodo(todoId);
        return ResponseEntity.ok(updatedTodo);
    }


    //build incomplete todo api
    @PatchMapping("{id}/incomplete")
    public ResponseEntity<TodoDto>  incompleteTodo(@PathVariable("id") Long todoId){
        TodoDto updatedTodo = todoService.incompleteTodo(todoId);
        return ResponseEntity.ok(updatedTodo);
    }

}