package com.springboot.awss3app.s3;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "application.aws.s3")
public class S3Configuration {

    private String s3BucketName;

}
