package com.geomgl.awsImageUpload.auth.repository;

import com.geomgl.awsImageUpload.auth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
