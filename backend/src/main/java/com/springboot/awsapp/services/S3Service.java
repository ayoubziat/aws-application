package com.springboot.awsapp.services;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import com.springboot.awsapp.configuration.S3Configuration;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class S3Service {

    private final S3Client s3Client;

    private final S3Configuration s3Configuration;

    public void uploadFileToS3(String key, MultipartFile file, Optional<Map<String, String>> optionalMetaData) {
        // convert the MultipartFile to a File which can be uploaded to S3
        File s3File = convertMultiPartFileToFile(file);
        if (s3File == null)
            throw new IllegalStateException("MultipartFile can not be uploaded! Converting it to a file did not work");

        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(s3Configuration.getS3BucketName())
                .key(key)
                .metadata(optionalMetaData.get())
                .build();
//        optionalMetaData.ifPresent(
//                map -> {
//                    if (!map.isEmpty()) {
//                        map.forEach(objectRequest::metadata);
//                    }
//                }
//        );

        try {
            s3Client.putObject(
                    objectRequest,
                    RequestBody.fromFile(s3File)
            );
        }
        catch (S3Exception exc) {
            throw new IllegalStateException("Failed to upload file to Amazon S3 bucket.", exc);
        }
    }

    public byte[] downloadFileFromS3(String imageS3Link) {
//        try {
//            S3Object s3Object = s3Client.getObject(
//                    s3Configuration.getS3BucketName(),
//                    imageS3Link
//            );
//            return IOUtils.toByteArray(
//                    s3Object.getObjectContent()
//            );
//        }
//        catch (AmazonServiceException exc) {
//            throw new IllegalStateException("Failed to download file from Amazon S3 bucket.", exc);
//        }
//        catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        return null;
    }

    private static File convertMultiPartFileToFile(MultipartFile multipartFile) {
        File s3File = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        try {
            s3File.createNewFile();
            FileOutputStream fos = new FileOutputStream(s3File);
            fos.write(multipartFile.getBytes());
            fos.close();
        } catch (IOException e) {
            System.out.println("IOException: "+ e);
            return null;
        }
        return s3File;
    }
}
