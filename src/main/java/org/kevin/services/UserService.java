package org.kevin.services;

import org.kevin.dto.request.UserRequest;
import org.kevin.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getAllUsers();
    UserResponse getUserById(Long id);
    UserResponse createUser(UserRequest userRequest);
    UserResponse updateUser(Long id, UserRequest userRequest);
    boolean deleteUser(Long id);

}
