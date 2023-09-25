package com.springboot.awss3app.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UploadFile {

    private final AmazonS3 s3Client;

    public void uploadFileToS3(String filePath, String fileName, Optional<Map<String, String>> optionalMetaData, InputStream inputStream) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        optionalMetaData.ifPresent(
                map -> {
                    if (!map.isEmpty()) {
                        map.forEach(objectMetadata::addUserMetadata);
                    }
                }
        );
        try {
            s3Client.putObject(filePath, fileName, inputStream, objectMetadata);
        }
        catch (AmazonServiceException exc) {
            throw new IllegalStateException("Failed to upload file to Amazon S3 bucket.", exc);
        }
    }
}
