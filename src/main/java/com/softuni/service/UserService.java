package com.softuni.service;

import com.softuni.model.entities.RoleNameEnum;
import com.softuni.model.entities.User;
import com.softuni.model.service.UserServiceModel;
import com.softuni.model.viewModels.UserProfileViewModel;

import java.util.List;

public interface UserService {
    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel findByUsernameAndPassword(String username, String password);

    void login(UserServiceModel user);

    void logout();

    List<String> findAllUserNames();

    void changeRole(String username, RoleNameEnum roleNameEnum);

    User findById(Long id);

    UserProfileViewModel findProfileById(Long id);

    Long findUserCount();


}
