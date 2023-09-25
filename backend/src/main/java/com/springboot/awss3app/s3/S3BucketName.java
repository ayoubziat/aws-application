package com.springboot.awss3app.s3;

public enum S3BucketName {

    BUCKET_NAME("az-files-s3-bucket");

    private final String s3BucketName;

    S3BucketName(String bucketName) {
        this.s3BucketName = bucketName;
    }

    public String getS3BucketName() { return this.s3BucketName; }
}
