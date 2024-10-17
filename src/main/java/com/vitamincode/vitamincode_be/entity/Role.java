package com.vitamincode.vitamincode_be.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(schema = "vitamincode_class", name = "`role`")
@Getter
@Setter
public class Role {
    @Id
    @Column(name = "id")
    private Integer roleId;

    @Column(name = "name")
    private String roleName;

    @Column(name = "description")
    private String roleDescription;

    @OneToMany(mappedBy = "role")
    @JsonIgnore
    private List<User> users;
}
