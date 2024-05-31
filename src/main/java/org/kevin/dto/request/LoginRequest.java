package org.kevin.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequest {
    @NotBlank(message = "Email tidak boleh kosong")
    @Email(message = "Email tidak valid")
    @Size(max = 255, message = "Email tidak boleh lebih dari 255 karakter")
    private String email;

    @NotBlank(message = "Password tidak boleh kosong")
    @Size(max = 1000, message = "Password tidak boleh lebih dari 1000 karakter")
    private String password;
}
