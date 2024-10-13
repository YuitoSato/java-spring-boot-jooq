package com.example.javaspringbootjooq.application.task;

import com.example.javaspringbootjooq.domain.task.Task;
import com.example.javaspringbootjooq.domain.task.TaskRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class UpdateTaskUseCaseIntegrationTest {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UpdateTaskUseCase updateTaskUseCase;

    @Test
    @Transactional
    void testUpdateTask() {
        // given
        Task task = new Task("title", "description");
        taskRepository.insert(task);

        // when
        UpdateTaskCommand updateTaskCommand = new UpdateTaskCommand(
            task.id(),
            "new title",
            "new description"
        );
        updateTaskUseCase.execute(updateTaskCommand);

        // then
        Task updatedTask = taskRepository.findById(task.id()).get();
        Assertions.assertEquals("new title", updatedTask.title());
        Assertions.assertEquals("new description", updatedTask.description());
    }
}
