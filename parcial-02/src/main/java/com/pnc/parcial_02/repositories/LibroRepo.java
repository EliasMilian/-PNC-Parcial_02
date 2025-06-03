package com.pnc.parcial_02.repositories;

import com.pnc.parcial_02.Domain.entities.Libro;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.UUID;
import java.util.List;
import java.util.Optional;


@Repository

public interface LibroRepo extends GeneralRepo <Libro, UUID>{

    public List<Libro> findAllByAuthor(String author);
    public List<Libro> findAllByLenguage(String language);
    public List<Libro> findAllByGenre(String genre);
    public Optional<Libro> findAllByIsbn(String isbn);
    public List<Libro> findAllByPagesBetween(int start, int end);
}
