package org.kevin.services;

import org.kevin.dto.request.UserRequest;
import org.kevin.dto.response.UserResponse;
import org.kevin.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserResponse> getAllUsers();
    UserResponse getUserById(Long id);
    Optional<User> findEmail(String email);
    UserResponse createAdmin(UserRequest userRequest);
    UserResponse updateUser(Long id, UserRequest userRequest);
    boolean deleteUser(Long id);

}
