package com.example.javaspringbootjooq.application;

public record CreateTaskCommand(
    String title, String description
) {
}
