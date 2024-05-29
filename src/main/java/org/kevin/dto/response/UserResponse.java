package org.kevin.dto.response;

import lombok.*;
import org.kevin.entities.enumPack.RoleEnum;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    public Long id;
    public String name;
    public String email;
    public String phoneNumber;
    public String address;
    public RoleEnum role;
}
