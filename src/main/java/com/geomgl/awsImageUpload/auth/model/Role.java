package com.geomgl.awsImageUpload.auth.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @SequenceGenerator(
            name="role_id_sequence",
            sequenceName="role_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "role_id_sequence"
    )
    private Long id;
    private String name;
}
