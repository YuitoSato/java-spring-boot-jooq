//package com.example.javaspringbootjooq.application.task.notmodelbase;
//
//import com.example.javaspringbootjooq.application.task.UpdateTaskCommand;
//import com.example.javaspringbootjooq.application.task.UpdateTaskUseCase;
//import org.jooq.DSLContext;
//import org.jooq.Record5;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//import static com.example.javaspringbootjooq.jooq.tables.Tasks.TASKS;
//
//@SpringBootTest
//@Transactional
//public class UpdateTaskUseCaseIntegrationTest {
//    @Autowired
//    private DSLContext jooq;
//    @Autowired
//    private UpdateTaskUseCase updateTaskUseCase;
//
//    @Test
//    void testUpdateTask() {
//        // given
//        jooq.insertInto(TASKS, TASKS.ID, TASKS.TITLE, TASKS.DESCRIPTION, TASKS.IMAGE_URL1, TASKS.IMAGE_URL2)
//            .values(1, "title", "description", "image1", "image2")
//            .execute();
//
//        // when
//        updateTaskUseCase.execute(
//            new UpdateTaskCommand(1, "new title", "new description", List.of("image3", "image4"))
//        );
//
//        // then
//        Record5<Integer, String, String, String, String> actual = jooq.select(TASKS.ID, TASKS.TITLE, TASKS.DESCRIPTION, TASKS.IMAGE_URL1, TASKS.IMAGE_URL2).from(TASKS)
//            .where(TASKS.ID.eq(1))
//            .fetchOptional().get();
//
//        Assertions.assertEquals("new title", actual.get(TASKS.TITLE));
//        Assertions.assertEquals("new description", actual.get(TASKS.DESCRIPTION));
//        Assertions.assertEquals("image3", actual.get(TASKS.IMAGE_URL1));
//        Assertions.assertEquals("image4", actual.get(TASKS.IMAGE_URL2));
//    }
//}
