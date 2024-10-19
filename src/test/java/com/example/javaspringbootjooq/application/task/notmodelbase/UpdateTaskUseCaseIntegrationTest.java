package com.example.javaspringbootjooq.application.task.notmodelbase;

import com.example.javaspringbootjooq.application.task.UpdateTaskCommand;
import com.example.javaspringbootjooq.application.task.UpdateTaskUseCase;
import org.jooq.DSLContext;
import org.jooq.Record3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static com.example.javaspringbootjooq.jooq.tables.Tasks.TASKS;

@SpringBootTest
@Transactional
public class UpdateTaskUseCaseIntegrationTest {
    @Autowired
    private DSLContext jooq;
    @Autowired
    private UpdateTaskUseCase updateTaskUseCase;

    @Test
    void testUpdateTask() {
        // given
        jooq.insertInto(TASKS, TASKS.ID, TASKS.TITLE, TASKS.DESCRIPTION)
            .values(1, "title", "description")
            .execute();

        // when
        updateTaskUseCase.execute(
            new UpdateTaskCommand(1, "new title", "new description")
        );

        // then
        Record3<Integer, String, String> actual = jooq.select(TASKS.ID, TASKS.TITLE, TASKS.DESCRIPTION).from(TASKS)
            .where(TASKS.ID.eq(1))
            .fetchOptional().get();

        Assertions.assertEquals("new title", actual.get(TASKS.TITLE));
        Assertions.assertEquals("new description", actual.get(TASKS.DESCRIPTION));
    }
}
