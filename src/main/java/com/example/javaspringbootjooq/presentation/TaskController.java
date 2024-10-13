package com.example.javaspringbootjooq.presentation;

import com.example.javaspringbootjooq.application.CreateTaskCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.javaspringbootjooq.application.CreateTaskUseCase;

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
