package com.springboot.awsapp.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "application.aws")
public class AWSProperties {

    private String awsAccessKeyId;
    private String awsSecretAccessKey;
    private String awsSessionToken;
    private String region;
}
