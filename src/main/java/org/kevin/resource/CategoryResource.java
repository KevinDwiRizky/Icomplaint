package org.kevin.resource;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.kevin.dto.request.CategoryRequest;
import org.kevin.dto.response.CategoryResponse;
import org.kevin.dto.response.WebResponse;
import org.kevin.services.CategoryService;

import java.util.List;

@Path("/category")
public class CategoryResource {
    @Inject
    private CategoryService categoryService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("ADMIN")
    public WebResponse<List<CategoryResponse>> getCategory() {
        List<CategoryResponse> categories = categoryService.getAllcategory();
        return WebResponse.<List<CategoryResponse>>builder()
                .status(Response.Status.OK.getStatusCode())
                .message("Categories found")
                .data(categories)
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("ADMIN")
    public WebResponse<CategoryResponse> getCategoryById(@PathParam("id") Long id) {
        CategoryResponse category = categoryService.getCategoryById(id);
        if (category == null) {
            return WebResponse.<CategoryResponse>builder()
                    .status(Response.Status.NOT_FOUND.getStatusCode())
                    .message("Category not found")
                    .build();
        }
        return WebResponse.<CategoryResponse>builder()
                .status(Response.Status.OK.getStatusCode())
                .message("Category found")
                .data(category)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("ADMIN")
    public WebResponse<CategoryResponse> createCategory(CategoryRequest categoryRequest) {
        CategoryResponse createdCategory = categoryService.createCategory(categoryRequest);
        return WebResponse.<CategoryResponse>builder()
                .status(Response.Status.CREATED.getStatusCode())
                .message("Category created successfully")
                .data(createdCategory)
                .build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("ADMIN")
    public WebResponse<CategoryResponse> updateCategory(@PathParam("id") Long id, CategoryRequest categoryRequest) {
        CategoryResponse updatedCategory = categoryService.updateCategory(id, categoryRequest);
        if (updatedCategory == null) {
            return WebResponse.<CategoryResponse>builder()
                    .status(Response.Status.NOT_FOUND.getStatusCode())
                    .message("Category not found")
                    .build();
        }
        return WebResponse.<CategoryResponse>builder()
                .status(Response.Status.OK.getStatusCode())
                .message("Category updated successfully")
                .data(updatedCategory)
                .build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("ADMIN")
    public WebResponse<Void> deleteCategory(@Valid @PathParam("id") Long id) {
        boolean deleted = categoryService.deleteCategory(id);
        if (deleted) {
            return WebResponse.<Void>builder()
                    .status(Response.Status.OK.getStatusCode())
                    .message("Category deleted successfully")
                    .build();
        } else {
            return WebResponse.<Void>builder()
                    .status(Response.Status.NOT_FOUND.getStatusCode())
                    .message("Category not found")
                    .build();
        }
    }
}
