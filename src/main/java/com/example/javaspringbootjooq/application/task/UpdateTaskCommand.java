package com.example.javaspringbootjooq.application.task;

import java.util.List;

public record UpdateTaskCommand(
    Integer id, String title, String description, List<String> imageUrls
) {
}
