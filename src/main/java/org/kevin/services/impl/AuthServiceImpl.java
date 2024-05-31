package org.kevin.services.impl;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.Claims;
import org.kevin.dto.request.LoginRequest;
import org.kevin.dto.request.UserRequest;
import org.kevin.dto.response.UserResponse;
import org.kevin.entities.User;
import org.kevin.entities.enumPack.RoleEnum;
import org.kevin.repositories.UserRepository;
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

    @Inject
    private UserRepository userRepository;

    @Override
    public Response login(LoginRequest loginRequest) {
        Optional<User> foundUser = userService.findEmail(loginRequest.getEmail());
        if (foundUser == null || !PasswordUtils.checkPassword(loginRequest.getPassword(), foundUser.get().getPassword())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }


        String token = Jwt.issuer("http://localhost:8080/issuer")
                .upn(foundUser.get().getEmail())
                .groups(foundUser.get().getRole().name())
                .claim("email", foundUser.get().getEmail())
                .claim(Claims.email_verified.name(), foundUser.get().getEmail())
                .sign();

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return Response.ok(response).build();
    }

    @Override
    @Transactional
    public UserResponse register(UserRequest userRequest) {
        String hashPassword = PasswordUtils.hashPassword(userRequest.getPassword());

        User user = User.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .address(userRequest.getAddress())
                .phoneNumber(userRequest.getPhoneNumber())
                .role(RoleEnum.USER)
                .password(hashPassword)
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


}
