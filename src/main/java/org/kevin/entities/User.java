package org.kevin.entities;

import jakarta.persistence.*;
import lombok.*;
import org.kevin.entities.enumPack.RoleEnum;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "\"User\"")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long Id;

    public String name;
    @Column(unique = true)
    public String email;
    @Column(name = "phone_number", unique = true)
    public String phoneNumber;
    public String address;
    @Enumerated(EnumType.STRING)
    public RoleEnum role;
}
