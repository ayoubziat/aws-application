package com.springboot.awss3app.datasource;

import com.springboot.awss3app.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AppDataSource {
    List<User> findAll();

    Optional<User> findById(UUID userId);
}
