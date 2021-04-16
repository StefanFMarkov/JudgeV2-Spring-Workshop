package com.softuni.service;

import com.softuni.model.entities.Role;
import com.softuni.model.entities.RoleNameEnum;

public interface RoleService {
    void initRoles();

    Role findRole(RoleNameEnum roleNameEnum);
}
