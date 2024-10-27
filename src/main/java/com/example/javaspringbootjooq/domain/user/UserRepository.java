package com.example.javaspringbootjooq.domain.user;

import java.util.Optional;

public interface UserRepository {

    void insert(User user);

    Optional<User> findById(Integer id);
}
