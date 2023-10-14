package com.springboot.awss3app.datasource.mock;

import com.springboot.awss3app.datasource.AppDataSource;
import com.springboot.awss3app.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("mock")
public class UsersDataSourceMock implements AppDataSource {

    private final List<User> usersList = List.of(
            new User(UUID.randomUUID(), "alex", null),
            new User(UUID.randomUUID(), "antonio", null)
    );


    @Override
    public List<User> findAll() {
        return usersList;
    }

    @Override
    public Optional<User> findById(UUID userId) {
        return usersList
                .stream()
                .filter(it -> it.getUserId().equals(userId)).findFirst();
    }

    @Override
    public Boolean existsById(UUID userId) {
        return this.findById(userId).isPresent();
    }
}
