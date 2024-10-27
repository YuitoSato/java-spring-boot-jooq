package com.example.javaspringbootjooq.domain.task;

import java.util.List;

public class TaskTestBuilder {

    private Integer userId = 1;
    private String title = "title";
    private String description = "description";
    private List<String> imageUrls = List.of("image1", "image2");

    public TaskTestBuilder userId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public TaskTestBuilder title(String title) {
        this.title = title;
        return this;
    }

    public TaskTestBuilder description(String description) {
        this.description = description;
        return this;
    }

    public TaskTestBuilder imageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
        return this;
    }

    public Task build() {
        return new Task(null, userId, title, description, imageUrls);
    }
}
