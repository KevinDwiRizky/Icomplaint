package org.kevin.services.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.kevin.dto.request.UserRequest;
import org.kevin.dto.response.UserResponse;
import org.kevin.entities.User;
import org.kevin.entities.enumPack.RoleEnum;
import org.kevin.repositories.UserRepository;
import org.kevin.services.UserService;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    @Inject
    UserRepository userRepository;


    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.listAll();
        return users.stream()
                .map(user -> UserResponse.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .email(user.getEmail())
                        .phoneNumber(user.getPhoneNumber())
                        .role(user.getRole())
                        .address(user.getAddress())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id);
        if (user == null) {
            return null;
        }
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .address(user.getAddress())
                .build();
    }

    @Override
    @Transactional
    public UserResponse createUser(UserRequest userRequest) {
        User user = User.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .address(userRequest.getAddress())
                .phoneNumber(userRequest.getPhoneNumber())
                .role(RoleEnum.USER)
                .build();

        userRepository.persist(user);

        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .address(user.getAddress())
                .role(user.getRole())
                .build();
    }

    @Override
    @Transactional
    public UserResponse updateUser(Long id, UserRequest userRequest) {
        User existingUser = userRepository.findById(id);
        if (existingUser == null) {
            return null;
        }
        existingUser.setName(userRequest.getName());
        existingUser.setAddress(userRequest.getAddress());
        existingUser.setEmail(userRequest.getEmail());
        existingUser.setPhoneNumber(userRequest.getPhoneNumber());

        userRepository.persist(existingUser);

        return UserResponse.builder()
                .id(existingUser.getId())
                .name(existingUser.getName())
                .email(existingUser.getEmail())
                .phoneNumber(existingUser.getPhoneNumber())
                .address(existingUser.getAddress())
                .role(existingUser.getRole())
                .build();
    }


    @Override
    @Transactional
    public boolean deleteUser(Long id) {
        User existingUser = userRepository.findById(id);
        if (existingUser != null) {
            userRepository.delete(existingUser);
            return true;
        } else {
            return false;
        }
    }

}
