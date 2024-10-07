package com.vitamincode.vitamincode_be.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = "vitamincode_class", name = "user")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue
    private Integer userId;

    @Column
    private String userName;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private Boolean enabled;
}
