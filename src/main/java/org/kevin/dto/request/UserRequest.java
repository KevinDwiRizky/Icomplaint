package org.kevin.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
    @NotBlank(message = "Nama tidak boleh kosong")
    @Size(max = 255, message = "Nama tidak boleh lebih dari 255 karakter")
    private String name;

    @NotBlank(message = "Email tidak boleh kosong")
    @Email(message = "Email tidak valid")
    @Size(max = 255, message = "Email tidak boleh lebih dari 255 karakter")
    private String email;

    @NotBlank(message = "Nomor telepon tidak boleh kosong")
    @Digits(integer = 12, fraction = 0, message = "Nomor telepon harus terdiri dari 12 digit angka")
    private String phoneNumber;

    @NotBlank(message = "Address tidak boleh kosong")
    @Size(max = 1000, message = "Address tidak boleh lebih dari 1000 karakter")
    private String address;
}
