package com.geomgl.awsImageUpload.auth.service;

import com.geomgl.awsImageUpload.auth.model.AppUser;
import com.geomgl.awsImageUpload.auth.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    AppUser getUser(String username);
    AppUser saveUser(AppUser user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    List<AppUser> getUsers();
}
