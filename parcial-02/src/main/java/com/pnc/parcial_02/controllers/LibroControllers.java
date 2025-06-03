package com.pnc.parcial_02.controllers;

import com.pnc.parcial_02.Domain.Dtos.Create.CreateLibroDTO.UpdateTitleDTO;
import com.pnc.parcial_02.Domain.Dtos.GenericResponse;
import com.pnc.parcial_02.Domain.entities.Libro;
import com.pnc.parcial_02.service.LibroService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/libros")
@RequiredArgsConstructor
public class LibroControllers  {

    private final LibroService libroService;


    @GetMapping("/author/{author}")
    public ResponseEntity<GenericResponse> findAllBooksByAuthor(@PathVariable String author) {
        List<Libro> lista = libroService.findAllBooksByAuthor(author);
        return GenericResponse.<List<Libro>>builder()
                .message("Libros encontrados por autor: " + author).data(lista).build().buildResponse();
    }


    @GetMapping("/language/{lenguage}")
    public ResponseEntity<GenericResponse> findBooksByLenguage(@PathVariable String lenguage) {
        try {
            List<Libro> lista = libroService.findBooksByLenguage(lenguage);
            return GenericResponse.<List<Libro>>builder()
                    .message("Libros encontrados por idioma: " + lenguage).data(lista).build().buildResponse();
        } catch (Exception ex) {
            return ResponseEntity.status(500)
                    .body(GenericResponse.builder()
                            .message("Error interno: " + ex.getMessage()).build());
        }
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<GenericResponse> findBooksByGenre(@PathVariable String genre) {
        try {
            List<Libro> lista = libroService.findBooksByGenre(genre);
            return GenericResponse.<List<Libro>>builder()
                    .message("Libros encontrados por género: " + genre).data(lista).build().buildResponse();
        } catch (Exception ex) {
            return ResponseEntity.status(500)
                    .body(GenericResponse.builder().message("Error interno: " + ex.getMessage()).build());
        }
    }


    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<GenericResponse> findBooksByIsbn(@PathVariable String isbn) {
        try {
            Optional<Libro> encontrados = libroService.findBooksByIsbn(isbn);
            if (encontrados.isEmpty()) {
                return ResponseEntity.notFound().build();
            } else {
                return GenericResponse.<List<Libro>>builder()
                        .message("Libro(s) encontrado(s) con ISBN: " + isbn).data(encontrados).build().buildResponse();
            }
        } catch (Exception ex) {
            return ResponseEntity.status(500)
                    .body(GenericResponse.builder()
                            .message("Error interno: " + ex.getMessage()).build());
        }
    }


    @GetMapping("/pages")
    public ResponseEntity<GenericResponse> findBooksByPagesRange(
            @RequestParam("start") int start,
            @RequestParam("end") int end) {

        try {
            List<Libro> lista = libroService.findBooksByPagesRange(start, end);
            return GenericResponse.<List<Libro>>builder()
                    .message("Libros con páginas entre " + start + " y " + end).data(lista).build().buildResponse();
        } catch (IllegalArgumentException ex) {
            // Si start > end, devolvemos 400 Bad Request con el mensaje de validación:
            return ResponseEntity.badRequest()
                    .body(GenericResponse.builder().message(ex.getMessage()).build());
        } catch (Exception ex) {
            return ResponseEntity.status(500)
                    .body(GenericResponse.builder().message("Error interno: " + ex.getMessage()).build());
        }
    }

    @PutMapping("/{id}/title-language")
    public ResponseEntity<GenericResponse> updateTitleLanguage(
            @PathVariable("id") UUID id,
            @RequestBody @Valid UpdateTitleDTO dto) {

        try {
            libroService.updateTitleAndLanguage(id, dto.getTitle(), dto.getLenguage());
            return GenericResponse.<Object>builder()
                    .message("Título e idioma actualizados correctamente").build().buildResponse();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(GenericResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(GenericResponse.builder()
                            .message("Error interno: " + e.getMessage())
                            .build());
        }
    }


    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    public ResponseEntity<GenericResponse> handleValidationErrors(
            org.springframework.web.bind.MethodArgumentNotValidException ex) {

        Map<String, String> errores = ex.getBindingResult()
                .getFieldErrors().stream().collect(Collectors.toMap(
                        err -> err.getField(),
                        err -> err.getDefaultMessage()
                ));

        return GenericResponse.<Map<String, String>>builder()
                .message("Error de validación").data(errores).build().buildResponse();
    }
}
