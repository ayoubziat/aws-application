package com.springboot.awss3app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
public class AwsS3Application {

	public static void main(String[] args) {
		SpringApplication.run(AwsS3Application.class, args);
	}

}
