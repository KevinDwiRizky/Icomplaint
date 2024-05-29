package org.kevin.resource;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.kevin.dto.request.CategoryRequest;
import org.kevin.dto.request.ChangeStatusComplaintRequest;
import org.kevin.dto.request.ComplaintRequest;
import org.kevin.dto.response.CategoryResponse;
import org.kevin.dto.response.ComplaintResponse;
import org.kevin.services.ComplaintService;

import java.util.List;

@Path("/complaint")
public class ComplaintResource {
    @Inject
    ComplaintService complaintService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ComplaintResponse> getComplaint() {
        return complaintService.getAllComplaint();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getComplaintById(@PathParam("id") Long id) {
        ComplaintResponse comlaint = complaintService.getComplaintById(id);
        if (comlaint == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(comlaint).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createComplaint(@Valid ComplaintRequest complaintRequest) {
        ComplaintResponse createdComplaint = complaintService.createComplaint(complaintRequest);

        return Response.ok().entity(createdComplaint).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateComplaint(@Valid @PathParam("id") Long id, ComplaintRequest complaintRequest) {
        ComplaintResponse updatedComplaint = complaintService.updateComplaint(id, complaintRequest);
        if (updatedComplaint == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(updatedComplaint).build();
    }

    @PUT
    @Path("/status/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response changeStatusComplaintRequest(@Valid @PathParam("id") Long id, ChangeStatusComplaintRequest changeStatusComplaintRequest) {
        ComplaintResponse changeStatusComplaint = complaintService.changeStatusComplaint(id, changeStatusComplaintRequest);
        if (changeStatusComplaint == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(changeStatusComplaint).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteComplaint(@PathParam("id") Long id) {
        boolean deleted = complaintService.deleteComplaint(id);

        if (deleted) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
