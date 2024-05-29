package org.kevin.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
    public String name;
    public String email;
    public String phoneNumber;
    public String address;
}
