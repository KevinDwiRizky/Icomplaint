package org.kevin.services.impl;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.Claims;
import org.kevin.dto.request.LoginRequest;
import org.kevin.entities.User;
import org.kevin.services.AuthService;
import org.kevin.services.UserService;
import org.kevin.utils.PasswordUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
public class AuthServiceImpl implements AuthService {
    @Inject
    private UserService userService;
    @Override
    public Response login(LoginRequest loginRequest) {
        Optional<User> foundUser = userService.findEmail(loginRequest.getEmail());
        if (foundUser == null || !PasswordUtils.checkPassword(loginRequest.getPassword(), foundUser.get().getPassword())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }


        String token = Jwt.issuer("http://localhost:8080/issuer")
                .upn(foundUser.get().getEmail())
                .groups(foundUser.get().getRole().name())
                .claim(Claims.email_verified.name(), foundUser.get().getEmail())
                .sign();

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return Response.ok(response).build();
    }
}
