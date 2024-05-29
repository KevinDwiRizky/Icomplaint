package org.kevin.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.kevin.entities.enumPack.StatusEnum;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChangeStatusComplaintRequest {
    @NotBlank(message = "Status tidak boleh kosong")
    private String status;
}

