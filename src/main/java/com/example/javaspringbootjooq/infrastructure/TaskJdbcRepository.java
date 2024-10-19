package com.example.javaspringbootjooq.infrastructure;

import com.example.javaspringbootjooq.domain.task.Task;
import com.example.javaspringbootjooq.domain.task.TaskRepository;
import com.example.javaspringbootjooq.jooq.tables.records.TasksRecord;
import com.example.javaspringbootjooq.utils.ListUtil;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.example.javaspringbootjooq.jooq.Tables.TASKS;

@Component
public class TaskJdbcRepository implements TaskRepository {

    private final DSLContext dsl;

    @Autowired
    public TaskJdbcRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    private static Task recordToTask(TasksRecord record) {
        List<String> imageUrls = new ArrayList<>(Arrays.asList(
            record.getImageUrl1(),
            record.getImageUrl2()
        )).stream().filter(Objects::nonNull).toList();

        return new Task(
            record.getId(),
            record.getTitle(),
            record.getTitle(),
            imageUrls
        );
    }

    @Override
    public void insert(Task task) {
        dsl.insertInto(TASKS)
            .set(TASKS.ID, task.id())
            .set(TASKS.TITLE, task.title())
            .set(TASKS.DESCRIPTION, task.description())
            .set(TASKS.IMAGE_URL1, task.imageUrls().get(0))
            .set(TASKS.IMAGE_URL2, task.imageUrls().get(1))
            .execute();
    }

    @Override
    public void update(Task task) {
        dsl.update(TASKS)
            .set(TASKS.TITLE, task.title())
            .set(TASKS.DESCRIPTION, task.description())
            .set(TASKS.IMAGE_URL1, ListUtil.getOrNull(task.imageUrls(), 0))
            .set(TASKS.IMAGE_URL2, ListUtil.getOrNull(task.imageUrls(), 1))
            .where(TASKS.ID.eq(task.id()))
            .execute();
    }

    @Override
    public Optional<Task> findById(Integer id) {
        return dsl.selectFrom(TASKS)
            .where(TASKS.ID.eq(id))
            .fetchOptional()
            .map(TaskJdbcRepository::recordToTask);
    }
}
