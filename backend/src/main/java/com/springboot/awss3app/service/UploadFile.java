package com.springboot.awss3app.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.springboot.awss3app.s3.S3Configuration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UploadFile {

    private final AmazonS3 s3Client;

    private final S3Configuration s3Configuration;

    public void uploadFileToS3(UUID userId, MultipartFile file, Optional<Map<String, String>> optionalMetaData) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        optionalMetaData.ifPresent(
                map -> {
                    if (!map.isEmpty()) {
                        map.forEach(objectMetadata::addUserMetadata);
                    }
                }
        );
        String key = String.format("%s/%s", userId, file.getOriginalFilename());
        try {
            s3Client.putObject(
                    s3Configuration.getS3BucketName(),
                    key,
                    file.getInputStream(),
                    objectMetadata
            );
        }
        catch (AmazonServiceException exc) {
            throw new IllegalStateException("Failed to upload file to Amazon S3 bucket.", exc);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
