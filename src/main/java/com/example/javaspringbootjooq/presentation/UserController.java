package com.example.javaspringbootjooq.presentation;

import com.example.javaspringbootjooq.domain.user.User;
import com.example.javaspringbootjooq.jooq.tables.records.UsersRecord;
import org.jooq.DSLContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.javaspringbootjooq.jooq.Tables.USERS;

@RestController
public class UserController {

    private final DSLContext dsl;

    public UserController(DSLContext dsl) {
        this.dsl = dsl;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        List<UsersRecord> usersRecords = dsl.selectFrom(USERS).fetch();

        return usersRecords.stream().map(record -> new User(
            record.getId(),
            record.getUserName(),
            record.getEmail()
        )).toList();
    }
}
