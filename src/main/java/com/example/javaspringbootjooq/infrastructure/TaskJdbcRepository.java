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
           // 他のフィールドも設定
           .execute();
    }

    @Override
    public Optional<Task> findById(Integer id) {
        Task task = dsl.selectFrom(TASKS)
                       .where(TASKS.ID.eq(id))
                       .fetchOneInto(Task.class);
        return Optional.ofNullable(task);
    }
}
