package vttp.paf.day22.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vttp.paf.day22.models.Task;

import static vttp.paf.day22.repositories.Queries.*;

@Repository
public class TaskRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Integer createTask(Task task) {

        return jdbcTemplate.update(SQL_INSERT_TASK, task.getTaskName(), task.getPriority().toString()
                , task.getAssignTo().getUsername(), task.getCompletionDate());

    }
}
