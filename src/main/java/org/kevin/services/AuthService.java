package org.kevin.services;

import jakarta.ws.rs.core.Response;
import org.kevin.dto.request.LoginRequest;

public interface AuthService {
    Response login(LoginRequest loginRequest);
}
