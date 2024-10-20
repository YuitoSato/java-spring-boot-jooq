package com.example.javaspringbootjooq.infrastructure;

import com.example.javaspringbootjooq.domain.task.Task;
import com.example.javaspringbootjooq.domain.task.TaskRepository;
import com.example.javaspringbootjooq.jooq.tables.records.ImagesRecord;
import com.example.javaspringbootjooq.jooq.tables.records.TasksRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.example.javaspringbootjooq.jooq.Tables.IMAGES;
import static com.example.javaspringbootjooq.jooq.Tables.TASKS;

@Component
public class TaskJdbcRepository implements TaskRepository {

    private final DSLContext dsl;

    @Autowired
    public TaskJdbcRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    private static Task recordToTask(TasksRecord taskRecord, List<ImagesRecord> imageRecords) {
        List<String> imageUrls = imageRecords.stream()
            .map(ImagesRecord::getImageUrl)
            .toList();

//        List<String> imageUrls = new ArrayList<>(Arrays.asList(
//            record.getImageUrl1(),
//            record.getImageUrl2()
//        )).stream().filter(Objects::nonNull).toList();

        return new Task(
            taskRecord.getId(),
            taskRecord.getTitle(),
            taskRecord.getDescription(),
            imageUrls
        );
    }

    @Override
    public void insert(Task task) {
        dsl.insertInto(TASKS)
            .set(TASKS.ID, task.id())
            .set(TASKS.TITLE, task.title())
            .set(TASKS.DESCRIPTION, task.description())
            .execute();

        task.imageUrls().forEach(imageUrl -> {
            dsl.insertInto(IMAGES)
                .set(IMAGES.TASK_ID, task.id())
                .set(IMAGES.IMAGE_URL, imageUrl)
                .execute();
        });
    }

    @Override
    public void update(Task task) {
        dsl.update(TASKS)
            .set(TASKS.TITLE, task.title())
            .set(TASKS.DESCRIPTION, task.description())
            .where(TASKS.ID.eq(task.id()))
            .execute();

        dsl.deleteFrom(IMAGES)
            .where(IMAGES.TASK_ID.eq(task.id()))
            .execute();

        task.imageUrls().forEach(imageUrl -> {
            dsl.insertInto(IMAGES)
                .set(IMAGES.TASK_ID, task.id())
                .set(IMAGES.IMAGE_URL, imageUrl)
                .execute();
        });
    }

    @Override
    public Optional<Task> findById(Integer id) {
        Optional<TasksRecord> tasksRecord = dsl.selectFrom(TASKS)
            .where(TASKS.ID.eq(id))
            .fetchOptional();

        List<ImagesRecord> imageRecords = dsl.selectFrom(IMAGES)
            .where(IMAGES.TASK_ID.eq(id))
            .fetch();

        return tasksRecord.map(record -> recordToTask(record, imageRecords));
    }
}
