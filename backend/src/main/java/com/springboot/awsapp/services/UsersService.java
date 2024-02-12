package com.springboot.awsapp.services;

import com.springboot.awsapp.domain.entities.UserEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface UsersService {

    List<UserEntity> getAllUsers();
    UserEntity getUserById(UUID userId);
    UserEntity uploadUserProfileImage(UUID userId, MultipartFile file);
    byte[] downloadUserProfileImage(UUID userId);
    UserEntity addUser(UserEntity userEntity);
}
