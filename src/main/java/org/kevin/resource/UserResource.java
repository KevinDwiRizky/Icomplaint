package org.kevin.resource;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.kevin.dto.request.UserRequest;
import org.kevin.dto.response.UserResponse;
import org.kevin.dto.response.WebResponse;
import org.kevin.services.UserService;

import java.util.List;

@Path("/user")
public class UserResource {
    @Inject
    private UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public WebResponse<List<UserResponse>> getUsers() {
        List<UserResponse> users = userService.getAllUsers();
        return WebResponse.<List<UserResponse>>builder()
                .status(Response.Status.OK.getStatusCode())
                .message("Users found")
                .data(users)
                .build();
    }

    @GET
    @Path("/{id}")
    public WebResponse<UserResponse> getUserById(@PathParam("id") Long id) {
        UserResponse user = userService.getUserById(id);
        if (user == null) {
            return WebResponse.<UserResponse>builder()
                    .status(Response.Status.NOT_FOUND.getStatusCode())
                    .message("User not found")
                    .build();
        }
        return WebResponse.<UserResponse>builder()
                .status(Response.Status.OK.getStatusCode())
                .message("User found")
                .data(user)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public WebResponse<UserResponse> createUser(@Valid UserRequest userRequest) {
        UserResponse createdUser = userService.createUser(userRequest);
        return WebResponse.<UserResponse>builder()
                .status(Response.Status.CREATED.getStatusCode())
                .message("User created successfully")
                .data(createdUser)
                .build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public WebResponse<UserResponse> updateUser(@PathParam("id") Long id, @Valid UserRequest userRequest) {
        UserResponse updatedUser = userService.updateUser(id, userRequest);
        if (updatedUser == null) {
            return WebResponse.<UserResponse>builder()
                    .status(Response.Status.NOT_FOUND.getStatusCode())
                    .message("User not found")
                    .build();
        }
        return WebResponse.<UserResponse>builder()
                .status(Response.Status.OK.getStatusCode())
                .message("User updated successfully")
                .data(updatedUser)
                .build();
    }

    @DELETE
    @Path("/{id}")
    public WebResponse<Void> deleteUser(@PathParam("id") Long id) {
        boolean deleted = userService.deleteUser(id);
        if (deleted) {
            return WebResponse.<Void>builder()
                    .status(Response.Status.OK.getStatusCode())
                    .message("User deleted successfully")
                    .build();
        } else {
            return WebResponse.<Void>builder()
                    .status(Response.Status.NOT_FOUND.getStatusCode())
                    .message("User not found")
                    .build();
        }
    }
}
