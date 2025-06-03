package com.pnc.parcial_02.service;

import com.pnc.parcial_02.Domain.entities.Libro;
import java.util.UUID;
import java.util.List;
import java.util.Optional;


public interface LibroService {

    public List findAllBooksByAuthor(String author) ;

    public List findBooksByLenguage (String lenguage) throws Exception ;

    public List findBooksByGenre(String genre) throws Exception ;

    public Optional<Libro> findBooksByIsbn(String isbn) throws Exception ;

    public List findBooksByPagesRange(int start, int end) throws Exception;

    void updateTitleAndLanguage(UUID id, String nuevoTitulo, String nuevoIdioma) throws Exception;

    public Libro createLibro(Libro nuevo) throws Exception;
}
