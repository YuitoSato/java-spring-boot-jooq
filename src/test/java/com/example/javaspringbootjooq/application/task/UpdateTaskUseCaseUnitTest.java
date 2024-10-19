package com.example.javaspringbootjooq.application.task;

import com.example.javaspringbootjooq.domain.task.Task;
import com.example.javaspringbootjooq.domain.task.TaskRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UpdateTaskUseCaseUnitTest {
    @Test
    void updateTaskTest() {
        // given
        TaskRepository taskRepository = mock(TaskRepository.class);
        Task task = new Task(1, "title", "description", List.of("image1", "image2"));
        when(taskRepository.findById(task.id())).thenReturn(Optional.of(task));

        // when
        UpdateTaskUseCase updateTaskUseCase = new UpdateTaskUseCase(taskRepository);
        updateTaskUseCase.execute(
            new UpdateTaskCommand(task.id(), "new title", "new description", List.of("image3", "image4"))
        );

        // then
        verify(taskRepository).update(new Task(1, "new title", "new description", List.of("image3", "image4")));
    }
}
