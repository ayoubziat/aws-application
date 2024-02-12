package com.springboot.awsapp.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "application.aws")
public class AWSProperties {

    private String awsAccessKeyId;
    private String awsSecretAccessKey;
    private String awsSessionToken;
    private String region;
}
