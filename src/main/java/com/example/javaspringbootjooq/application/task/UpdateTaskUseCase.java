package com.example.javaspringbootjooq.application.task;

import com.example.javaspringbootjooq.domain.task.Task;
import com.example.javaspringbootjooq.domain.task.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UpdateTaskUseCase {

    private final TaskRepository taskRepository;

    public UpdateTaskUseCase(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void execute(UpdateTaskCommand command) {
        Optional<Task> taskOpt = taskRepository.findById(command.id());

        if (taskOpt.isEmpty()) {
            throw new RuntimeException("Task not found");
        }

        Task task = taskOpt.get();

        Task updated = task.update(command.title(), command.description(), command.imageUrls());

        taskRepository.update(updated);
    }
}
