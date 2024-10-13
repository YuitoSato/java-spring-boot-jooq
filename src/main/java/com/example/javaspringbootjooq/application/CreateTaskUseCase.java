package com.example.javaspringbootjooq.application;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.javaspringbootjooq.domain.task.Task;
import com.example.javaspringbootjooq.domain.task.TaskRepository;

@Service
public class CreateTaskUseCase {

    @Autowired
    private final TaskRepository taskRepository;

    public CreateTaskUseCase(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void execute(CreateTaskCommand command) {
        Task task = new Task(command.title(), command.description());

        taskRepository.insert(task);
    }
}
