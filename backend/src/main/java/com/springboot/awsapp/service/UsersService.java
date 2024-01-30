package com.springboot.awsapp.service;

import com.springboot.awsapp.domain.entities.UserEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface UsersService {

    List<UserEntity> getAllUsers();
    UserEntity getUserById(UUID userId);
    void uploadUserImage(UUID userId, MultipartFile file);
    byte[] downloadUserImage(UUID userId);
}
