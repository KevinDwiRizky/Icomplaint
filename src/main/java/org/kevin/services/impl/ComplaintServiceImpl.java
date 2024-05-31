package org.kevin.services.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.kevin.dto.request.CreateComplaintRequest;
import org.kevin.exception.ResourceNotFoundException;
import org.kevin.dto.request.ChangeStatusComplaintRequest;
import org.kevin.dto.request.ComplaintRequest;
import org.kevin.dto.response.ComplaintResponse;
import org.kevin.entities.Category;
import org.kevin.entities.Complaint;
import org.kevin.entities.User;
import org.kevin.entities.enumPack.StatusEnum;
import org.kevin.repositories.CategoryRepository;
import org.kevin.repositories.ComplaintRepository;
import org.kevin.repositories.UserRepository;
import org.kevin.services.ComplaintService;
import org.kevin.services.UserService;
import org.kevin.utils.JwtUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class ComplaintServiceImpl implements ComplaintService {
    @Inject
    private ComplaintRepository complaintRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private UserService userService;

    @Inject
    private CategoryRepository categoryRepository;

    @Inject
    private JwtUtils jwtUtils;

    @Override
    public List<ComplaintResponse> getAllComplaint() {
        List<Complaint> complaints = complaintRepository.listAll();
        return complaints.stream()
                .map(complaint -> ComplaintResponse.builder()
                        .id(complaint.getId())
                        .title(complaint.getTitle())
                        .description(complaint.getDescription())
                        .status(complaint.getStatus())
                        .createdAt(complaint.getCreatedAt())
                        .updatedAt(complaint.getUpdatedAt())
                        .user(complaint.getUser())
                        .category(complaint.getCategory())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public ComplaintResponse getComplaintById(Long id) {
        Complaint complaint = complaintRepository.findById(id);
        if (complaint == null) {
            return null;
        }
        return ComplaintResponse.builder()
                .id(complaint.getId())
                .title(complaint.getTitle())
                .description(complaint.getDescription())
                .status(complaint.getStatus())
                .createdAt(complaint.getCreatedAt())
                .updatedAt(complaint.getUpdatedAt())
                .user(complaint.getUser())
                .category(complaint.getCategory())
                .build();
    }

    @Override
    @Transactional
    public ComplaintResponse createComplaint(CreateComplaintRequest complaintRequest) {
        String emailFromToken = jwtUtils.getEmailFromToken();
        Optional<User> getIdFromEmailToken = userService.findEmail(emailFromToken);
        User getUserById = userRepository.findById(getIdFromEmailToken.get().Id);

        if (getUserById == null) {
            throw new ResourceNotFoundException("User not found");
        }

        Category getCategoryById = categoryRepository.findById(complaintRequest.getCategoryId());
        if (getCategoryById == null) {
            throw new ResourceNotFoundException("Category not found with ID: " + complaintRequest.getCategoryId());
        }

        Complaint complaint = Complaint.builder()
                .title(complaintRequest.getTitle())
                .description(complaintRequest.getDescription())
                .status(StatusEnum.NEW)
                .user(getUserById)
                .category(getCategoryById)
                .build();

        complaintRepository.persist(complaint);

        return ComplaintResponse.builder()
                .id(complaint.getId())
                .title(complaint.getTitle())
                .description(complaint.getDescription())
                .status(complaint.getStatus())
                .createdAt(complaint.getCreatedAt())
                .updatedAt(complaint.getUpdatedAt())
                .user(complaint.getUser())
                .category(complaint.getCategory())
                .build();
    }

    @Override
    @Transactional
    public ComplaintResponse updateComplaint(Long id, ComplaintRequest complaintRequest) {
        Complaint existingComplaint = complaintRepository.findById(id);
        if (existingComplaint == null) {
            throw new ResourceNotFoundException("Complaint not found with ID: " + id);
        }

        User getUserById = userRepository.findById(complaintRequest.getUserId());
        if (getUserById == null) {
            throw new ResourceNotFoundException("User not found with ID: " + complaintRequest.getUserId());
        }

        Category getCategoryById = categoryRepository.findById(complaintRequest.getCategoryId());
        if (getCategoryById == null) {
            throw new ResourceNotFoundException("Category not found with ID: " + complaintRequest.getCategoryId());
        }

        existingComplaint.setTitle(complaintRequest.getTitle());
        existingComplaint.setDescription(complaintRequest.getDescription());
        existingComplaint.setUser(getUserById);
        existingComplaint.setCategory(getCategoryById);

        complaintRepository.persist(existingComplaint);

        return ComplaintResponse.builder()
                .id(existingComplaint.getId())
                .title(existingComplaint.getTitle())
                .description(existingComplaint.getDescription())
                .status(existingComplaint.getStatus())
                .createdAt(existingComplaint.getCreatedAt())
                .updatedAt(existingComplaint.getUpdatedAt())
                .user(existingComplaint.getUser())
                .category(existingComplaint.getCategory())
                .build();
    }

    @Override
    public ComplaintResponse changeStatusComplaint(Long id, ChangeStatusComplaintRequest changeStatusComplaintRequest) {

        Complaint complaint = complaintRepository.findById(id);
        if (complaint == null) {
            throw new ResourceNotFoundException("Complaint not found with ID: " + id);
        }

        if (Objects.equals(changeStatusComplaintRequest.getStatus(), "IN_PROGRESS")) {
            complaint.setStatus(StatusEnum.IN_PROGRESS);
        } else if (Objects.equals(changeStatusComplaintRequest.getStatus(), "RESOLVED")) {
            complaint.setStatus(StatusEnum.RESOLVED);
        } else if (Objects.equals(changeStatusComplaintRequest.getStatus(), "REJECTED")) {
            complaint.setStatus(StatusEnum.REJECTED);
        } else {
            throw new ResourceNotFoundException("Complaint status not found with !");
        }
        complaintRepository.persist(complaint);

        return ComplaintResponse.builder()
                .id(complaint.getId())
                .title(complaint.getTitle())
                .description(complaint.getDescription())
                .status(complaint.getStatus())
                .createdAt(complaint.getCreatedAt())
                .updatedAt(complaint.getUpdatedAt())
                .user(complaint.getUser())
                .category(complaint.getCategory())
                .build();
    }

    @Override
    @Transactional
    public boolean deleteComplaint(Long id) {
        Complaint existingComplaint = complaintRepository.findById(id);

        if (existingComplaint != null) {
            complaintRepository.delete(existingComplaint);
            return true;
        } else {
            return false;
        }
    }
}
