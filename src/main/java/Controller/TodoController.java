package Controller;

import Models.Status;
import Models.Todo;
import Service.TodoService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }
    @GetMapping("/")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/todos")
    public List<Todo> getAllTodos() throws SQLException, ClassNotFoundException {
        return todoService.getAllTodos();
    }

    @DeleteMapping("todos/{id}")
    public void deleteTodoById(@PathVariable (required = true) int id){
        todoService.deleteTodoById(id);
    }

    @GetMapping("todos/{id}")
    public Todo showById(@PathVariable(required = true) int id){
        return  todoService.showById(id);
    }

            @PostMapping("/todo")
            public void create(@RequestBody Todo toAdd){
                todoService.create(toAdd);
            }

    @GetMapping("/search")
    public List<Todo> findByStatus(@RequestParam Status state) {
        return todoService.findByStatus(state);
    }
}
