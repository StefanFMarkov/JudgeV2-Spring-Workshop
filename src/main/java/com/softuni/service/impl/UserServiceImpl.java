package com.softuni.service.impl;

import com.softuni.model.entities.RoleNameEnum;
import com.softuni.model.entities.User;
import com.softuni.model.service.UserServiceModel;
import com.softuni.model.viewModels.UserProfileViewModel;
import com.softuni.repository.UserRepository;
import com.softuni.security.CurrentUser;
import com.softuni.service.RoleService;
import com.softuni.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleService roleService;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RoleService roleService, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
        this.currentUser = currentUser;
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {
        User user = modelMapper.map(userServiceModel, User.class);
        user.setRole(roleService.findRole(RoleNameEnum.USER));

        userRepository.save(user);
    }

    @Override
    public UserServiceModel findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password)
                .map(user -> modelMapper.map(user, UserServiceModel.class)).orElse(null);
    }

    @Override
    public void login(UserServiceModel user) {
        currentUser.setId(user.getId());
        currentUser.setUsername(user.getUsername());
        currentUser.setRole(user.getRole().getName());

    }

    @Override
    public void logout() {
        this.currentUser.setUsername(null)
                .setId(null)
                .setRole(null);
    }

    @Override
    public List<String> findAllUserNames() {
        return userRepository.findAllUsernames();
    }

    @Override
    public void changeRole(String username, RoleNameEnum roleNameEnum) {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            if (user.get().getRole().getName() != roleNameEnum) {
                user.get().setRole(roleService.findRole(roleNameEnum));
            }
            userRepository.save(user.get());
        }
    }

    @Override
    public User findById(Long id) {
        return this.userRepository.findById(id)
                .orElse(null);
    }

    @Override
    public UserProfileViewModel findProfileById(Long id) {
        User user = this.userRepository.findById(id).orElse(null);

        return modelMapper.map(user, UserProfileViewModel.class)
                .setHomeworkSet(
                        user.getHomeworkSet().stream()
                                .map(homework -> homework.getExercise().getName())
                                .collect(Collectors.toSet()));
    }

    @Override
    public Long findUserCount() {
        return userRepository.count();
    }
}
