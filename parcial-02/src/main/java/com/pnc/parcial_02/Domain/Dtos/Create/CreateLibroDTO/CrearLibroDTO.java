package com.pnc.parcial_02.Domain.Dtos.Create.CreateLibroDTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrearLibroDTO {

    @NotBlank(message = "El título no puede estar vacío")
    private String title;

    @NotBlank(message = "El autor no puede estar vacío")
    private String author;

    @NotBlank(message = "El ISBN no puede estar vacío")
    private String isbn;

    @NotBlank(message = "El año de publicación no puede estar vacío")
    private String pubYear;

    @NotBlank(message = "El idioma no puede estar vacío")
    private String lenguage;

    @NotNull(message = "Las páginas son obligatorias")
    @Positive(message = "El número de páginas debe ser un entero positivo")
    private Integer pages;

    @NotBlank(message = "El género no puede estar vacío")
    private String genre;
}
