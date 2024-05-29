package org.kevin.dto.request;


import lombok.*;
import org.kevin.entities.enumPack.StatusEnum;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComplaintRequest {
    private String title;
    private String description;
    private Long userId;
    private Long categoryId;
}

