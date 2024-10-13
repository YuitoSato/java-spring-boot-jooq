package com.example.javaspringbootjooq.domain.user;

public interface UserRepository {

    void insert(User user);

    User findById(Integer id);
}
