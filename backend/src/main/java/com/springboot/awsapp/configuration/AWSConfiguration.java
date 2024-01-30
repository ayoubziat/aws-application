package com.springboot.awsapp.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.*;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class AWSConfiguration {

    @Autowired
    private AWSProperties awsProperties;

    @Bean
    public AwsCredentialsProvider awsCredentialsProvider() {
        return StaticCredentialsProvider.create(
                AwsSessionCredentials.create(
                        awsProperties.getAwsAccessKeyId(),
                        awsProperties.getAwsSecretAccessKey(),
                        awsProperties.getAwsSessionToken()
                )
        );
    }

    @Bean
    public S3Client s3Client(AwsCredentialsProvider awsCredentialsProvider) {
        return S3Client.builder()
                .region(Region.of(awsProperties.getRegion()))
                .credentialsProvider(awsCredentialsProvider)
                .build();
    }
}
