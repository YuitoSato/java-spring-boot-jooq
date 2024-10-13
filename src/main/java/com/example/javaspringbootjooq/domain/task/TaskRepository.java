package com.example.javaspringbootjooq.domain.task;

import java.util.Optional;

public interface TaskRepository {

    void insert(Task task);

    void update(Task task);

    Optional<Task> findById(Integer id);
}
