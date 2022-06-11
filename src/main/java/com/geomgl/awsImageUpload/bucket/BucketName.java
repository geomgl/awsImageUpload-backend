package com.geomgl.awsImageUpload.bucket;

public enum BucketName {

    PROFILE_IMAGE("aws-image-upload-memes");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return this.bucketName;
    }
}
