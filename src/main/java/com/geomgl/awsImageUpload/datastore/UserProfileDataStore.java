package com.geomgl.awsImageUpload.datastore;

import com.geomgl.awsImageUpload.profile.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

@Repository
public class UserProfileDataStore {
    private static final List<UserProfile> USER_PROFILES = new ArrayList<>();

    // static blocks get run the first time that the class is loaded into memory
    static {
        USER_PROFILES.add(new UserProfile(UUID.fromString("8444ecaa-fbc9-4661-900c-4611028df33e"), "fakeUsername1", null));
        USER_PROFILES.add(new UserProfile(UUID.fromString("c11982ed-42bb-4f05-a5ea-60cfe118272f"), "fakeUsername2", null));
        USER_PROFILES.add(new UserProfile(UUID.fromString("e73ae278-e92a-4680-a21a-ada2bd9d02cd"), "fakeUsername3", null));
    }

    public List<UserProfile> getUserProfiles() {
        return USER_PROFILES;
    }
}
