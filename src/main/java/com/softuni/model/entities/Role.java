package com.softuni.model.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name =  "roles")
public class Role extends BaseEntity {

    private RoleNameEnum name;

    public Role(RoleNameEnum name) {
        this.name = name;
    }

    public Role() {

    }

    @Enumerated(EnumType.STRING)
    public RoleNameEnum getName() {
        return name;
    }

    public void setName(RoleNameEnum name) {
        this.name = name;
    }
}
