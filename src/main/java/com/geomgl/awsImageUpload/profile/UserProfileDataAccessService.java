package com.geomgl.awsImageUpload.profile;

import com.geomgl.awsImageUpload.datastore.UserProfileDataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserProfileDataAccessService {

    // the below line of code allows for dependency injection
    // we can easily change what ProfileDataStore that is used, especially if
    // ProfileDataStore is made into an interface
    private final UserProfileDataStore userProfileDataStore;

    @Autowired
    public UserProfileDataAccessService(UserProfileDataStore userProfileDataStore) {
        this.userProfileDataStore = userProfileDataStore;
    }

    List<UserProfile> getUserProfiles() {
        return userProfileDataStore.getUserProfiles();
    }

}
