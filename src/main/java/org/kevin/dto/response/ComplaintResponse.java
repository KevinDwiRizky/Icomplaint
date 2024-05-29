package org.kevin.dto.response;

import lombok.*;
import org.kevin.entities.Category;
import org.kevin.entities.User;
import org.kevin.entities.enumPack.StatusEnum;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComplaintResponse {
    private Long id;
    private String title;
    private String description;
    private StatusEnum status;
    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;
    private User user;
    private Category category;
}
