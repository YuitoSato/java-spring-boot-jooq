package com.example.javaspringbootjooq.application;

import com.example.javaspringbootjooq.domain.task.Task;
import com.example.javaspringbootjooq.domain.task.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateTaskUseCase {

    private final TaskRepository taskRepository;

    public CreateTaskUseCase(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void execute(CreateTaskCommand command) {
        Task task = new Task(command.title(), command.description());

        taskRepository.insert(task);
    }
}
