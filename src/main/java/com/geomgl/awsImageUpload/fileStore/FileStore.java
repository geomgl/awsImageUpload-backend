package com.geomgl.awsImageUpload.fileStore;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;

import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

@Service
public class FileStore {
    private final AmazonS3 s3;

    @Autowired
    public FileStore(AmazonS3 s3) {
        this.s3 = s3;
    }

    public void save (String path,
                      String fileName,
                      Optional<Map<String, String>> optionalMetadata,
                      InputStream fileInputStream) {
        ObjectMetadata metadata = new ObjectMetadata();

        // get any provided metadata
        optionalMetadata.ifPresent((map) -> {
            if (!map.isEmpty()) {
                map.forEach((key, value) -> metadata.addUserMetadata(key, value));
                // the following line should be equivalent to the above
                // map.forEach(metadata::addUserMetadata);
            }
        });

        // save to s3 bucket
        try {
            s3.putObject(path, fileName, fileInputStream, metadata);
        } catch (AmazonServiceException e) {
            throw new IllegalStateException("failed to store file to s3", e);
        }
    }

    public byte[] download(String path, String filename) {
        try {
            S3Object bucketObj = s3.getObject(path, filename);
            S3ObjectInputStream inputStream = bucketObj.getObjectContent();
            System.out.println("Successfully downloaded image from s3.");
            return IOUtils.toByteArray(inputStream);
        } catch(Exception e) {
            throw new IllegalStateException("Failed to download image from s3.");
        }
    }
}
