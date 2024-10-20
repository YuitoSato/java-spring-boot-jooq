package com.example.javaspringbootjooq.application.task;

import com.example.javaspringbootjooq.domain.TestSeedData;
import com.example.javaspringbootjooq.domain.task.Task;
import com.example.javaspringbootjooq.domain.task.TaskRepository;
import com.example.javaspringbootjooq.domain.task.TaskTestBuilder;
import com.example.javaspringbootjooq.domain.user.UserRepository;
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
    private UserRepository userRepository;
    @Autowired
    private UpdateTaskUseCase updateTaskUseCase;

    @Test
    void タスクを更新できること() {
        // given
        Integer user1Id = TestSeedData.user1Id;
        Task task = new TaskTestBuilder().userId(user1Id).build();
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
