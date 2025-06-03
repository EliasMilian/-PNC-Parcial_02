package com.pnc.parcial_02.Domain.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Libro ")
public class Libro {
    @Id
@GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String title;

    @Column
    private String author;

    @Column (unique = true)
    private String isbn;

    @Column
    private String pubYear;

    @Column(nullable = true)
    private String lenguage;

    @Column
    private Integer pages;

    @Column
    private String genre;
}
