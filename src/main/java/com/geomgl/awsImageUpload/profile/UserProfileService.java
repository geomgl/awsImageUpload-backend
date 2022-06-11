package com.geomgl.awsImageUpload.profile;

import com.geomgl.awsImageUpload.bucket.BucketName;
import com.geomgl.awsImageUpload.fileStore.FileStore;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class UserProfileService {

    private final UserProfileDataAccessService userProfileDataAccessService;
    private final FileStore fileStore;

    @Autowired
    public UserProfileService(UserProfileDataAccessService userProfileDataAccessService, FileStore fileStore) {
        this.userProfileDataAccessService = userProfileDataAccessService;
        this.fileStore = fileStore;
    }

    List<UserProfile> getUserProfiles() {
        return userProfileDataAccessService.getUserProfiles();
    }

    public void uploadUserProfileImage(UUID userProfileId, MultipartFile file) {
        // check if image is empty
        if (file.isEmpty()) {
            throw new IllegalStateException("File cannot be empty");
        }

        // make sure file is an image
        if (!Arrays.asList(ContentType.IMAGE_GIF.getMimeType(),
                ContentType.IMAGE_PNG.getMimeType(),
                ContentType.IMAGE_JPEG.getMimeType()).contains(file.getContentType())) {
            throw new IllegalStateException("File must be an image");
        }


        // check if user is in database
        UserProfile user = getUser(userProfileId);

        // get metadata from file
        Map<String, String> metadata = extractMetaData(file);

        // Store image in s3 and update database with image link
        // we must specify the path for where to find the image in the bucket via a param to s3.putObject()
        // with the following, we would access our images in the bucket via: bucketname/profileId/fileName

        // Note that we also specify the filename, we could just use the filename but for uniqueness we can
        // append a random UUID to the actual filename

        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), user.getUserProfileId());
        String filename = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());

        try {
            fileStore.save(path, filename, Optional.of(metadata), file.getInputStream());
            user.setUserProfileImageLink(filename);
            System.out.println("Image successfully uploaded to bucket!");
        } catch(IOException e) {
            // we need to catch the IOExcpetion that getInputStream() may throw
            throw new IllegalStateException(e);
        }
    }

    public byte[] downloadUserProfileImage(UUID userProfileId) {
        UserProfile user = getUser(userProfileId);
        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), user.getUserProfileId());
        String filename = user.getUserProfileImageLink();
        return fileStore.download(path, filename);
    }

    private Map<String, String> extractMetaData(MultipartFile file) {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content -type", file.getContentType());
        metadata.put("Content-length", String.valueOf(file.getSize()));
        return metadata;
    }

    private UserProfile getUser(UUID userProfileId) {
        return userProfileDataAccessService.getUserProfiles()
                .stream()
                .filter(profile -> profile.getUserProfileId().equals(userProfileId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("user profile %s not found", userProfileId)));
    }
}
