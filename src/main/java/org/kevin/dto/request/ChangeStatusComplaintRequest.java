package org.kevin.dto.request;


import lombok.*;
import org.kevin.entities.enumPack.StatusEnum;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChangeStatusComplaintRequest {
    private String status;
}

