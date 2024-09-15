package todolist.todojava2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"Controller","Models","Repository","Service"})

public class TodoJava2Application {

    public static void main(String[] args) {
        SpringApplication.run(TodoJava2Application.class, args);
    }

}
