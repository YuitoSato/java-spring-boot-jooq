package com.example.javaspringbootjooq.application.task;

import java.util.List;

public record CreateTaskCommand(
    String title, String description, List<String> imageUrls
) {
}
