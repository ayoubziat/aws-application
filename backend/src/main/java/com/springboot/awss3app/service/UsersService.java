package com.springboot.awss3app.service;

import com.springboot.awss3app.datasource.AppDataSource;
import com.springboot.awss3app.model.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UsersService {

    @Qualifier("mock")
    private AppDataSource dataSource;


    public List<User> getAllUsers() {
        return dataSource.findAll();
    }

    public User getUserById(UUID userId) {
        return this.dataSource.findById(userId).orElseThrow(
                () -> new NoSuchElementException("User with ID '" + userId + "' could not be found")
        );
    }

    public void uploadUserImage(UUID userId, MultipartFile file) {
        // 1. Check if image is not empty
        // 2. Check if file is an image
        // 3. Check if the user exists in database
        // 4. Grab some metdata from file if any
        // 5. Store image in S3 and update DB with s3 image path (userImageLink)
    }
}
