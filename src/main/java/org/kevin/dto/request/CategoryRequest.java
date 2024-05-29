package org.kevin.dto.request;

import lombok.AllArgsConstructor;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryRequest {

    @NotBlank(message = "Nama kategori tidak boleh kosong")
    @Size(max = 255, message = "Nama kategori tidak boleh lebih dari 255 karakter")
    private String name;

    @NotBlank(message = "Deskripsi kategori tidak boleh kosong")
    @Size(max = 1000, message = "Deskripsi kategori tidak boleh lebih dari 1000 karakter")
    private String description;
}
