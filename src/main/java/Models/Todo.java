package Models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class Todo {
    private int id;
    private String title;
    private String description;
    private LocalDateTime creationDate;
    private LocalDateTime deadline;
    private LocalDateTime executionDate;
    private Priority priority;
    private Status state;

    public Todo(int id, String title, String description, LocalDateTime creationDate, LocalDateTime deadline, LocalDateTime executionDate, Priority priority, Status state) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
        this.deadline = deadline;
        this.executionDate = executionDate;
        this.priority = priority;
        this.state = state;
    }
}
