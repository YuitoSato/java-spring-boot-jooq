package com.example.javaspringbootjooq.application.task.notmodelbase;

import com.example.javaspringbootjooq.application.task.UpdateTaskCommand;
import com.example.javaspringbootjooq.application.task.UpdateTaskUseCase;
import org.jooq.DSLContext;
import org.jooq.Record3;
import org.jooq.Table;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static com.example.javaspringbootjooq.jooq.tables.Tasks.TASKS;

@SpringBootTest
@Transactional
public class UpdateTaskUseCaseCSVIntegrationTest {
    @Autowired
    private DSLContext jooq;
    @Autowired
    private UpdateTaskUseCase updateTaskUseCase;

    @Test
    void testUpdateTask() throws IOException {
        // given
        loadCSVData(TASKS, "src/test/resources/tasks.csv");

        // when
        updateTaskUseCase.execute(
            new UpdateTaskCommand(1, "new title", "new description", List.of("image"))
        );

        // then
        Record3<Integer, String, String> actual = jooq.select(TASKS.ID, TASKS.TITLE, TASKS.DESCRIPTION).from(TASKS)
            .where(TASKS.ID.eq(1))
            .fetchOptional().get();

        Assertions.assertEquals("new title", actual.get(TASKS.TITLE));
        Assertions.assertEquals("new description", actual.get(TASKS.DESCRIPTION));
    }

    // CSVデータのロード処理をヘルパーメソッドとして分離
    private void loadCSVData(Table tasks, String filePath) throws IOException {
        try (InputStream inputstream = new FileInputStream(filePath)) {
            jooq.loadInto(tasks)
                .loadCSV(inputstream, "UTF-8")
                .fields(tasks.fields())
                .execute();
        }
    }
}
