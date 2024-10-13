package com.example.javaspringbootjooq.application.task;

import com.example.javaspringbootjooq.domain.task.Task;
import com.example.javaspringbootjooq.domain.task.TaskRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UpdateTaskUseCaseUnitTest {

    @Test
    void updateTaskTest() {
        // given
        TaskRepository taskRepository = mock(TaskRepository.class);
        Task task = new Task(1, "title", "description");
        when(taskRepository.findById(task.id())).thenReturn(Optional.of(task));
        UpdateTaskUseCase updateTaskUseCase = new UpdateTaskUseCase(taskRepository);

        // when
        UpdateTaskCommand updateTaskCommand = new UpdateTaskCommand(task.id(), "new title", "new description");
        updateTaskUseCase.execute(updateTaskCommand);

        // then
        verify(taskRepository).update(new Task(
            1,
            "new title",
            "new description"
        ));
    }
}
