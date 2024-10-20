package com.example.javaspringbootjooq.domain.task;

import com.example.javaspringbootjooq.domain.RandomIdGenerator;

import java.util.List;
import java.util.Objects;

public class Task {
    private final Integer id;
    private final Integer userId;
    private final String title;
    private final String description;
    private final List<String> imageUrls;

    public Task(Integer userId, String title, String description, List<String> imageUrls) {
        if (title.isEmpty()) throw new IllegalArgumentException("タイトルは1文字で以上ある必要があります");
        if (description.isEmpty()) throw new IllegalArgumentException("詳細は1文字以上である必要があります");
        this.id = RandomIdGenerator.generate();
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.imageUrls = imageUrls;
    }

    public Task(Integer id, Integer userId, String title, String description, List<String> imageUrls) {
        if (title.isEmpty()) throw new IllegalArgumentException("タイトルは1文字で以上ある必要があります");
        if (description.isEmpty()) throw new IllegalArgumentException("詳細は1文字以上である必要があります");
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.imageUrls = imageUrls;
    }

    public Integer id() {
        return id;
    }

    public String title() {
        return title;
    }

    public String description() {
        return description;
    }

    public List<String> imageUrls() {
        return imageUrls;
    }

    public Integer userId() {
        return userId;
    }

    public Task update(String title, String description, List<String> imageUrls) {
        return new Task(this.id, this.userId, title, description, imageUrls);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) && Objects.equals(title, task.title) && Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description);
    }
}
