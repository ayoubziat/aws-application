package com.springboot.awss3app.configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class AWSConfig {

    private final AWSProperties awsProperties;

    @Bean
    public AmazonS3 createAmazonS3Client() {
        AWSCredentials awsCredentials = new BasicSessionCredentials(
                awsProperties.getAwsAccessKeyId(),
                awsProperties.getAwsSecretAccessKey(),
                awsProperties.getAwsSessionToken()
        );

        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }
}
