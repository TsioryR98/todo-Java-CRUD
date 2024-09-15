package Service;

import Models.Status;
import Models.Todo;
import Repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }                       
    public List<Todo> getAllTodos() {
        return todoRepository.readAll();
    }
    public void deleteTodoById(int id) {
        todoRepository.delete(id);
    }
    public Todo showById(int id){
        return todoRepository.readOne(id);
    }
    public void create(Todo toAdd){
        todoRepository.create(toAdd);
    }
    public List<Todo> findByStatus(Status state){
        return todoRepository.searchByStates(state);
    }
}
