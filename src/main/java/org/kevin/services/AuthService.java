package org.kevin.services;

import jakarta.ws.rs.core.Response;
import org.kevin.dto.request.LoginRequest;
import org.kevin.dto.request.UserRequest;
import org.kevin.dto.response.UserResponse;

public interface AuthService {
    Response login(LoginRequest loginRequest);
    UserResponse register(UserRequest userRequest);
}
