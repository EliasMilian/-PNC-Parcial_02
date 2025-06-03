package com.pnc.parcial_02.service.ServiceImpl;


import com.pnc.parcial_02.Domain.Dtos.Create.CreateLibroDTO.CrearLibroDTO;
import com.pnc.parcial_02.Domain.Dtos.GenericResponse;
import com.pnc.parcial_02.Domain.entities.Libro;
import com.pnc.parcial_02.repositories.LibroRepo;
import com.pnc.parcial_02.service.LibroService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LibroServiceImpl implements LibroService {

private final LibroRepo libroRepo;


@Override
   public List<Libro> findAllBooksByAuthor(String author)  {
    return libroRepo.findAllByAuthor(author);
}
@Override
    public List<Libro> findBooksByLenguage (String lenguage){

    return libroRepo.findAllByLenguage(lenguage);
}

@Override
    public List<Libro> findBooksByGenre(String genre) {
    return libroRepo.findAllByGenre(genre);
}

@Override
    public Optional<Libro> findBooksByIsbn(String isbn){
    return libroRepo.findAllByIsbn(isbn);
}
    @Override
    public List<Libro> findBooksByPagesRange(int start, int end) throws Exception {
        if (start > end) {
            throw new IllegalArgumentException("El valor inicial no puede ser mayor que el final");
        }
        return libroRepo.findAllByPagesBetween(start, end);
    }

    @Override
    public void updateTitleAndLanguage(UUID id, String nuevoTitulo, String nuevoIdioma) throws Exception {
        Libro existente = libroRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe Libro con id " + id));

        if (nuevoTitulo == null || nuevoTitulo.isBlank()) {
            throw new IllegalArgumentException("El título no puede estar vacío");
        }
        if (nuevoIdioma == null || nuevoIdioma.isBlank()) {
            throw new IllegalArgumentException("El idioma no puede estar vacío");
        }
        existente.setTitle(nuevoTitulo);
        existente.setLenguage(nuevoIdioma);
        libroRepo.save(existente);
    }
    @Override
    public Libro createLibro(Libro nuevo) throws Exception {
        var existentes = libroRepo.findAllByIsbn(nuevo.getIsbn());
        if (!existentes.isEmpty()) {
            throw new EntityExistsException("Ya existe un libro con ISBN: " + nuevo.getIsbn());
        }
        return libroRepo.save(nuevo);
    }



}
