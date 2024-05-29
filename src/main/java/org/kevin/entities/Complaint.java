package org.kevin.entities;

import jakarta.persistence.*;
import lombok.*;
import org.kevin.entities.enumPack.StatusEnum;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long Id;

    public String title;
    public String description;
    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    public StatusEnum status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }}
