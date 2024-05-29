package org.kevin.dto.request;


import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.kevin.entities.enumPack.StatusEnum;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComplaintRequest {
    @NotBlank(message = "Judul tidak boleh kosong")
    @Size(max = 255, message = "Judul tidak boleh lebih dari 255 karakter")
    private String title;

    @NotBlank(message = "Deskripsi tidak boleh kosong")
    @Size(max = 1000, message = "Deskripsi tidak boleh lebih dari 1000 karakter")
    private String description;

    @NotNull(message = "ID pengguna tidak boleh kosong")
    @Digits(integer = 255, fraction = 0, message = "Harus Berisi Angka")
    private Long userId;

    @NotNull(message = "ID kategori tidak boleh kosong")
    @Digits(integer = 255, fraction = 0, message = "Harus Berisi Angka")
    private Long categoryId;
}

