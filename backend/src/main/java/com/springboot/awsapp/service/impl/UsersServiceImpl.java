package com.springboot.awsapp.service.impl;

import com.springboot.awsapp.domain.entities.UserEntity;
import com.springboot.awsapp.repository.UsersRepository;
import com.springboot.awsapp.service.S3Service;
import com.springboot.awsapp.service.UsersService;
import lombok.AllArgsConstructor;
import org.apache.hc.core5.http.ContentType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Stream;

import static org.apache.hc.core5.http.ContentType.*;


@Service
@AllArgsConstructor
public class UsersServiceImpl implements UsersService {

    private UsersRepository usersRepository;

    private S3Service s3Service;

    private static final List<String> ALLOWED_FILE_TYPES =
            Stream.of(IMAGE_JPEG, IMAGE_PNG, IMAGE_GIF).map(ContentType::getMimeType).toList();

    public List<UserEntity> getAllUsers() {
        return this.usersRepository.findAll();
    }

    public UserEntity getUserById(UUID userId) {
        return this.usersRepository.findById(userId).orElseThrow(
                () -> new NoSuchElementException("User with ID '" + userId + "' could not be found")
        );
    }

    public void uploadUserImage(UUID userId, MultipartFile file) {
        // Check if image is not empty
        if(file.isEmpty())
            throw new IllegalStateException("File is empty. Empty File can not be uploaded.");

        // Check if file is an image
        if(!ALLOWED_FILE_TYPES.contains(file.getContentType()))
            throw new IllegalStateException("File must be an image. File type: " + file.getContentType());

        // Check if the user exists in DB
        UserEntity userEntity = this.usersRepository.findById(userId).orElseThrow(
                () -> new NoSuchElementException("User with ID ("+ userId + ") does not exist")
        );

        // Extract metadata from file
        Map<String, String> metaData = extractMetaData(file);

        // Store image in S3 and update DB with s3 image path (userImageLink)
        String key = String.format("%s/%s", userId, file.getOriginalFilename());
        s3Service.uploadFileToS3(key, file, Optional.of(metaData));
        userEntity.setUserImageLink(key);
    }

    public byte[] downloadUserImage(UUID userId) {
        // Check if the user exists in DB
        UserEntity userEntity = this.usersRepository.findById(userId).orElseThrow(
                () -> new NoSuchElementException("User with ID ("+ userId + ") does not exist")
        );

        String userImageLink = userEntity.getUserImageLink().orElseThrow(
                () -> new IllegalStateException("No image was uploaded yet for user with ID (" + userId + "). Please upload a image first!!")
        );

        return s3Service.downloadFileFromS3(userImageLink);
    }

    private static Map<String, String> extractMetaData(MultipartFile file) {
        Map<String, String> metaData = new HashMap<>();
        metaData.put("Content-Type", file.getContentType());
        metaData.put("Content-Length", String.valueOf(file.getSize()));
        return metaData;
    }
}
