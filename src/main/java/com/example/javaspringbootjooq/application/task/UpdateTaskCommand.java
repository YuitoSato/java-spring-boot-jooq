package com.example.javaspringbootjooq.application.task;

public record UpdateTaskCommand(
    Integer id, String title, String description
) {
}
