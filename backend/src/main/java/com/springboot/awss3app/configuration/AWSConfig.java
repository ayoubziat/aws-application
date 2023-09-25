package com.springboot.awss3app.configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSConfig {

    @Value("${application.aws.access-key-id}")
    private String awsAccessKey;

    @Value("${application.aws.access-key-id}")
    private String awsSecretKey;

    @Bean
    public AmazonS3 createAmazonS3Client() {
        AWSCredentials awsCredentials = new BasicAWSCredentials(
                awsAccessKey,
                awsSecretKey
        );

        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }
}
