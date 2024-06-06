package org.kevin.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.kevin.entities.enumPack.RoleEnum;
import org.kevin.entities.enumPack.StatusEnum;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChangeStatusComplaintRequest {
    private StatusEnum status;
}

