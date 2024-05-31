package org.kevin.resource;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.kevin.dto.request.LoginRequest;
import org.kevin.dto.request.UserRequest;
import org.kevin.dto.response.UserResponse;
import org.kevin.dto.response.WebResponse;
import org.kevin.services.AuthService;

@Path("/auth")
public class AuthResource {
    @Inject
    private AuthService authService;

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public WebResponse<UserResponse> createUser(@Valid UserRequest userRequest) {
        UserResponse createdUser = authService.register(userRequest);
        return WebResponse.<UserResponse>builder()
                .status(Response.Status.CREATED.getStatusCode())
                .message("Register successfully")
                .data(createdUser)
                .build();
    }
}
