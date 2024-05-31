package org.kevin.resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.kevin.dto.request.ChangeStatusComplaintRequest;
import org.kevin.dto.request.ComplaintRequest;
import org.kevin.dto.request.CreateComplaintRequest;
import org.kevin.dto.response.ComplaintResponse;
import org.kevin.dto.response.WebResponse;
import org.kevin.services.ComplaintService;

import java.util.List;

@Path("/complaint")
public class ComplaintResource {
    @Inject
    private ComplaintService complaintService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public WebResponse<List<ComplaintResponse>> getComplaints() {
        List<ComplaintResponse> complaints = complaintService.getAllComplaint();
        return WebResponse.<List<ComplaintResponse>>builder()
                .status(Response.Status.OK.getStatusCode())
                .message("Complaints found")
                .data(complaints)
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public WebResponse<ComplaintResponse> getComplaintById(@PathParam("id") Long id) {
        ComplaintResponse complaint = complaintService.getComplaintById(id);
        if (complaint == null) {
            return WebResponse.<ComplaintResponse>builder()
                    .status(Response.Status.NOT_FOUND.getStatusCode())
                    .message("Complaint not found")
                    .build();
        }
        return WebResponse.<ComplaintResponse>builder()
                .status(Response.Status.OK.getStatusCode())
                .message("Complaint found")
                .data(complaint)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("USER")
    public WebResponse<ComplaintResponse> createComplaint(@Valid CreateComplaintRequest complaintRequest) {
        ComplaintResponse createdComplaint = complaintService.createComplaint(complaintRequest);
        return WebResponse.<ComplaintResponse>builder()
                .status(Response.Status.CREATED.getStatusCode())
                .message("Complaint created successfully")
                .data(createdComplaint)
                .build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("USER")
    public WebResponse<ComplaintResponse> updateComplaint(@PathParam("id") Long id, @Valid ComplaintRequest complaintRequest) {
        ComplaintResponse updatedComplaint = complaintService.updateComplaint(id, complaintRequest);
        if (updatedComplaint == null) {
            return WebResponse.<ComplaintResponse>builder()
                    .status(Response.Status.NOT_FOUND.getStatusCode())
                    .message("Complaint not found")
                    .build();
        }
        return WebResponse.<ComplaintResponse>builder()
                .status(Response.Status.OK.getStatusCode())
                .message("Complaint updated successfully")
                .data(updatedComplaint)
                .build();
    }

    @PUT
    @Path("/status/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("ADMIN")
    public WebResponse<ComplaintResponse> changeStatusComplaint(@PathParam("id") Long id, @Valid ChangeStatusComplaintRequest changeStatusComplaintRequest) {
        ComplaintResponse changedStatusComplaint = complaintService.changeStatusComplaint(id, changeStatusComplaintRequest);
        if (changedStatusComplaint == null) {
            return WebResponse.<ComplaintResponse>builder()
                    .status(Response.Status.NOT_FOUND.getStatusCode())
                    .message("Complaint not found")
                    .build();
        }
        return WebResponse.<ComplaintResponse>builder()
                .status(Response.Status.OK.getStatusCode())
                .message("Complaint status changed successfully")
                .data(changedStatusComplaint)
                .build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("ADMIN")
    public WebResponse<Void> deleteComplaint(@PathParam("id") Long id) {
        boolean deleted = complaintService.deleteComplaint(id);
        if (deleted) {
            return WebResponse.<Void>builder()
                    .status(Response.Status.OK.getStatusCode())
                    .message("Complaint deleted successfully")
                    .build();
        } else {
            return WebResponse.<Void>builder()
                    .status(Response.Status.NOT_FOUND.getStatusCode())
                    .message("Complaint not found")
                    .build();
        }
    }
}
