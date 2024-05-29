package org.kevin.resource;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.kevin.dto.request.UserRequest;
import org.kevin.dto.response.UserResponse;
import org.kevin.entities.User;
import org.kevin.services.UserService;
import org.w3c.dom.stylesheets.LinkStyle;

import java.net.URI;
import java.util.List;

@ApplicationScoped
@Path("/user")
public class UserResource {
    @Inject
    UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserResponse> getUsers() {
        return userService.getAllUsers();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentById(@PathParam("id") Long id) {
        UserResponse user = userService.getUserById(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(user).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(UserRequest userRequest) {
        UserResponse createdStudent = userService.createUser(userRequest);

        return Response.ok().entity(createdStudent).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("id") Long id, UserRequest userRequest) {
        UserResponse updatedUser = userService.updateUser(id, userRequest);
        if (updatedUser == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(updatedUser).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") Long id) {
        boolean deleted = userService.deleteUser(id);

        if (deleted) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


}
