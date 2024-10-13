package com.example.javaspringbootjooq.domain.task;

import com.example.javaspringbootjooq.domain.RandomIdGenerator;

import java.util.Objects;

public class Task {
    private final Integer id;
    private final String title;
    private final String description;

    public Task(String title, String description) {
        this.id = RandomIdGenerator.generate();
        this.title = title;
        this.description = description;
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
