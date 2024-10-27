package com.example.javaspringbootjooq.infrastructure;

import com.example.javaspringbootjooq.domain.user.User;
import com.example.javaspringbootjooq.domain.user.UserRepository;
import org.jooq.DSLContext;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.example.javaspringbootjooq.jooq.Tables.USERS;

@Component
public class UserJdbcRepository implements UserRepository {

    private final DSLContext jooq;

    UserJdbcRepository(DSLContext dsl) {
        this.jooq = dsl;
    }

    @Override
    public void insert(User user) {
        jooq.insertInto(USERS)
            .set(USERS.ID, user.id())
            .set(USERS.USER_NAME, user.name())
            .set(USERS.EMAIL, user.email())
            .execute();
    }

    @Override
    public Optional<User> findById(Integer id) {
        return jooq.selectFrom(USERS)
            .where(USERS.ID.eq(id))
            .fetchOptional().map(record -> new User(
                record.getId(),
                record.getUserName(),
                record.getEmail()
            ));
    }
}
