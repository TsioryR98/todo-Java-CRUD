package Repository;

import Models.Priority;
import Models.Status;
import Models.Todo;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Repository
public class TodoRepository implements TodoDAO{
    DataBaseConnect dataBaseConnect = new DataBaseConnect();
    @Override
    public void create(Todo toAdd) {
        String query = "INSERT INTO todoTable (id, title, description, creationDate, deadline, executionDate, priority, state ) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dataBaseConnect.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setInt(1, toAdd.getId());
            statement.setString(2, toAdd.getTitle());
            statement.setString(3, toAdd.getDescription());
            statement.setObject(4, toAdd.getCreationDate());
            statement.setObject(5, toAdd.getDeadline());
            statement.setObject(6, toAdd.getExecutionDate());
            statement.setString(7, toAdd.getPriority().name());
            statement.setString(8, toAdd.getState().name());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error creating Todo", e);
        }

    }

    @Override
    public List<Todo> readAll() {
        List<Todo> todolist = new ArrayList<>();

        try (Statement stm = dataBaseConnect.getConnection().createStatement()) {
            String query = "SELECT * FROM todotable";
            ResultSet result = stm.executeQuery(query);

            while (result.next()) {
                Todo toAdd = new Todo(
                        result.getInt("id"),
                        result.getString("title"),
                        result.getString("description"),
                        result.getObject("creationdate", LocalDateTime.class),
                        result.getObject("deadline", LocalDateTime.class),
                        result.getObject("executiondate", LocalDateTime.class),
                        Priority.valueOf(result.getString("priority")),
                        Status.valueOf(result.getString("state"))
                );
                todolist.add(toAdd);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error reading one Todo", e);
        }
        return todolist;
    }

    public Todo readOne(int id) {
        Todo todoRead = null;
        String query = "SELECT * FROM todotable WHERE id=?";

        try (Connection conn = dataBaseConnect.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
                if (result.next()) {
                    todoRead = new Todo(
                            result.getInt("id"),
                            result.getString("title"),
                            result.getString("description"),
                            result.getObject("creationDate", LocalDateTime.class),
                            result.getObject("deadline", LocalDateTime.class),
                            result.getObject("executiondate", LocalDateTime.class),
                            Priority.valueOf(result.getString("priority")),
                            Status.valueOf(result.getString("state"))
                    );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error reading Todo", e);
        }
        return todoRead;
    }

    @Override
    public Todo update(int id, Todo toUpdate) {
        String query =  "UPDATE todotable SET title=?, description=?, creationdate=?, deadline=?, executiondate=?, priority=?, state=? WHERE id=?";
        try(Connection conn = dataBaseConnect.getConnection();
            PreparedStatement statement = conn.prepareStatement(query)){
            statement.setString(1,toUpdate.getTitle());
            statement.setString(2,toUpdate.getDescription());
            statement.setObject(3,Timestamp.valueOf(toUpdate.getCreationDate()));
            statement.setObject(4,Timestamp.valueOf(toUpdate.getDeadline()));
            statement.setObject(5,Timestamp.valueOf(toUpdate.getExecutionDate()));
            statement.setString(6, String.valueOf(toUpdate.getPriority()));
            statement.setString(7, String.valueOf(toUpdate.getState()));
            statement.setInt(8,toUpdate.getId());

            statement.executeUpdate();
            return toUpdate;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM todotable WHERE id=?";
        try (Connection conn = dataBaseConnect.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting Todo", e);
        }
    }

    public List<Todo> searchByStates(Status state) {
        List<Todo> todolist = new ArrayList<>();
            String query = "SELECT * FROM todotable WHERE state=?";
        try(Connection conn = dataBaseConnect.getConnection();
            PreparedStatement statement = conn.prepareStatement(query)){
            statement.setString(1,state.toString());
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Todo toAdd;
                toAdd = new Todo(
                        result.getInt("id"),
                        result.getString("title"),
                        result.getString("description"),
                        result.getObject("creationdate", LocalDateTime.class),
                        result.getObject("deadline", LocalDateTime.class),
                        result.getObject("executiondate", LocalDateTime.class),
                        Priority.valueOf(result.getString("priority")),
                        Status.valueOf(result.getString("state"))
                );
                todolist.add(toAdd);
            }
            return todolist;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
