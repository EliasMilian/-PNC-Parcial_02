package com.pnc.parcial_02.Domain.Dtos.Create.CreateLibroDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTitleDTO {
    @NotBlank(message = "El título no puede estar vacío")
    private String title;

    @NotBlank(message = "El idioma no puede estar vacío")
    private String lenguage;
}
