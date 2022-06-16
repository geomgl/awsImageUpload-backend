package com.geomgl.awsImageUpload.bucket;

public enum BucketName {

    PHOTOGRAM_IMAGES("geomgl-photogram-image-uploads");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return this.bucketName;
    }
}
