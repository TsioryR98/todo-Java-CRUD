package Repository;

import Models.Status;
import Models.Todo;

import java.util.List;

public interface TodoDAO {
    void create(Todo toAdd);
    List<Todo> readAll();
    Todo update (int id, Todo toUpdate);
    void delete (int id);
}
