package org.kevin.resource;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.kevin.dto.request.CategoryRequest;
import org.kevin.dto.request.UserRequest;
import org.kevin.dto.response.CategoryResponse;
import org.kevin.dto.response.UserResponse;
import org.kevin.services.CategoryService;

import java.util.List;

@Path("/category")
public class CategoryResource {
    @Inject
    CategoryService categoryService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CategoryResponse> getCategory() {
        return categoryService.getAllcategory();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategoryById(@PathParam("id") Long id) {
        CategoryResponse category = categoryService.getCategoryById(id);
        if (category == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(category).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCategory(CategoryRequest categoryRequest) {
        CategoryResponse createdCategory = categoryService.createCategory(categoryRequest);

        return Response.ok().entity(createdCategory).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCategory(@PathParam("id") Long id, CategoryRequest categoryRequest) {
        CategoryResponse updatedcategory = categoryService.updateCategory(id, categoryRequest);
        if (updatedcategory == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(updatedcategory).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCategory(@Valid @PathParam("id") Long id) {
        boolean deleted = categoryService.deleteCategory(id);

        if (deleted) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
