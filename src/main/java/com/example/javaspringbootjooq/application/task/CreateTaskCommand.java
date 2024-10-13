package com.example.javaspringbootjooq.application.task;

public record CreateTaskCommand(
    String title, String description
) {
}
