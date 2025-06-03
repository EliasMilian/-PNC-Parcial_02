package com.pnc.parcial_02.controllers;

import com.pnc.parcial_02.service.LibroService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/libro")
public class LibroControllers {

    private final LibroService libroService;


}
