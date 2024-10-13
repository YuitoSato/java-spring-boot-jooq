package com.example.javaspringbootjooq.presentation;

import com.example.javaspringbootjooq.application.task.CreateTaskCommand;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.javaspringbootjooq.application.task.CreateTaskUseCase;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private CreateTaskUseCase createTaskUseCase;

    @PostMapping
    public ResponseEntity<Void> createTask(@RequestBody CreateTaskCommand command) {
        createTaskUseCase.execute(command);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
