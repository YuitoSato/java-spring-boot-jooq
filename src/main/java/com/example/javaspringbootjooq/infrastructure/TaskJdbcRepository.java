package com.example.javaspringbootjooq.infrastructure;

import com.example.javaspringbootjooq.domain.task.Task;
import com.example.javaspringbootjooq.domain.task.TaskRepository;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.example.javaspringbootjooq.jooq.Tables.TASKS;

@Component
public class TaskJdbcRepository implements TaskRepository {

    private final DSLContext dsl;

    @Autowired
    public TaskJdbcRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    @Override
    public void insert(Task task) {
        dsl.insertInto(TASKS)
            .set(TASKS.ID, task.id())
            .set(TASKS.TITLE, task.title())
            .set(TASKS.DESCRIPTION, task.description())
            .execute();
    }

    @Override
    public void update(Task task) {
        dsl.update(TASKS)
            .set(TASKS.TITLE, task.title())
            .set(TASKS.DESCRIPTION, task.description())
            .where(TASKS.ID.eq(task.id()))
            .execute();
    }

    @Override
    public Optional<Task> findById(Integer id) {
        return dsl.selectFrom(TASKS)
            .where(TASKS.ID.eq(id))
            .fetchOptional()
            .map(record -> new Task(
                record.get(TASKS.ID),
                record.get(TASKS.TITLE),
                record.get(TASKS.DESCRIPTION)
            ));
    }
}
