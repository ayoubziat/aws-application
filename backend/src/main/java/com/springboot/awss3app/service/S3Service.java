package com.springboot.awss3app.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
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
public class S3Service {

    private final AmazonS3 s3Client;

    private final S3Configuration s3Configuration;

    public void uploadFileToS3(String key, MultipartFile file, Optional<Map<String, String>> optionalMetaData) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        optionalMetaData.ifPresent(
                map -> {
                    if (!map.isEmpty()) {
                        map.forEach(objectMetadata::addUserMetadata);
                    }
                }
        );
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

    public byte[] downloadFileFromS3(String imageS3Link) {
        try {
            S3Object s3Object = s3Client.getObject(
                    s3Configuration.getS3BucketName(),
                    imageS3Link
            );
            return IOUtils.toByteArray(
                    s3Object.getObjectContent()
            );
        }
        catch (AmazonServiceException exc) {
            throw new IllegalStateException("Failed to download file from Amazon S3 bucket.", exc);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
