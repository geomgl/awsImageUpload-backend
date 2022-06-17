package com.geomgl.awsImageUpload.auth.repository;

import com.geomgl.awsImageUpload.auth.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
    AppUser save(AppUser user);
}
