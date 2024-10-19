package com.example.javaspringbootjooq.application.task;

import com.example.javaspringbootjooq.domain.task.Task;
import com.example.javaspringbootjooq.domain.task.TaskRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class UpdateTaskUseCaseIntegrationTest {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UpdateTaskUseCase updateTaskUseCase;

    @Test
    void testUpdateTask() {
        // given
        Task task = new Task("title", "description", List.of("image1", "image2"));
        taskRepository.insert(task);

        // when
        updateTaskUseCase.execute(
            new UpdateTaskCommand(task.id(), "new title", "new description", List.of("image3", "image4"))
        );

        // then
        Task updatedTask = taskRepository.findById(task.id()).get();
        Assertions.assertEquals("new title", updatedTask.title());
        Assertions.assertEquals("new description", updatedTask.description());
        Assertions.assertEquals(List.of("image3", "image4"), updatedTask.imageUrls());
    }
}
