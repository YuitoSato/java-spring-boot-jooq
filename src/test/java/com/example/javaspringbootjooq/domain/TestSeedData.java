package com.example.javaspringbootjooq.domain;

import com.example.javaspringbootjooq.domain.user.User;
import com.example.javaspringbootjooq.domain.user.UserRepository;
import com.example.javaspringbootjooq.domain.user.UserTestBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.Rollback;

@Component
public class TestSeedData {

    public static Integer user1Id = 1;
    @Autowired
    private UserRepository userRepository;

    TestSeedData(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }

    @Rollback(false)
    public void create() {
        User user = new UserTestBuilder()
            .id(user1Id)
            .build();

        userRepository.insert(user);
    }
}
