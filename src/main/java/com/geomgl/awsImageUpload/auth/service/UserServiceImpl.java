package com.geomgl.awsImageUpload.auth.service;

import com.geomgl.awsImageUpload.auth.model.AppUser;
import com.geomgl.awsImageUpload.auth.model.Role;
import com.geomgl.awsImageUpload.auth.repository.RoleRepository;
import com.geomgl.awsImageUpload.auth.repository.AppUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private AppUserRepository appUserRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(AppUserRepository appUserRepository, RoleRepository roleRepository) {
        this.appUserRepository = appUserRepository;
        this.roleRepository = roleRepository;
    }

    public UserServiceImpl() { }

    public AppUser getUser(String username) {
        return appUserRepository.findByUsername(username);
    }

    public AppUser saveUser(AppUser user) {
        return appUserRepository.save(user);
    }

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    public void addRoleToUser(String username, String roleName) {
        AppUser user = appUserRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    public List<AppUser> getUsers() {
        return appUserRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = appUserRepository.findByUsername(username);
        if (user == null) {
            log.error("User not found");
            throw new UsernameNotFoundException("User not found");
        } else {
            log.info("User found.");
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach((role) -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}
