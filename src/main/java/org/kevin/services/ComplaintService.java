package org.kevin.services;

import org.kevin.dto.request.ChangeStatusComplaintRequest;
import org.kevin.dto.request.ComplaintRequest;
import org.kevin.dto.request.CreateComplaintRequest;
import org.kevin.dto.request.UserRequest;
import org.kevin.dto.response.ComplaintResponse;
import org.kevin.dto.response.UserResponse;

import java.util.List;

public interface ComplaintService {
    List<ComplaintResponse> getAllComplaint();
    ComplaintResponse getComplaintById(Long id);
    ComplaintResponse createComplaint(CreateComplaintRequest complaintRequest);
    ComplaintResponse updateComplaint(Long id, ComplaintRequest complaintRequest);
    ComplaintResponse changeStatusComplaint(Long id, ChangeStatusComplaintRequest changeStatusComplaintRequest);
    boolean deleteComplaint(Long id);
}
