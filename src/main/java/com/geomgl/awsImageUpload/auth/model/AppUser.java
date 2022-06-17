package com.geomgl.awsImageUpload.auth.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {

    @Id
    @SequenceGenerator(
            name="user_id_sequence",
            sequenceName="user_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_id_sequence"
    )
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;

    // load role types whenever we load users
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();
}
