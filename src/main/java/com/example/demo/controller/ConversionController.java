package com.example.demo.controller;

import com.example.demo.model.Conversion;
import com.example.demo.service.ConversionService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/conversion")
public class ConversionController {

    @Autowired
    private ConversionService conversionService;

    // Endpoint para convertir y guardar
    @GetMapping
    public Conversion convertir(
            @RequestParam String from,
            @RequestParam String to
    ) {
        return conversionService.convertirYGuardar(from, to);
    }

    // Endpoint para historial de conversiones
    @GetMapping("/historial")
    public List<Conversion> historial() {
        return conversionService.obtenerHistorial();
    }

    @GetMapping("/historial/buscar")
    public Optional<Conversion> buscar(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam String date
    ) {
        LocalDate localDate = LocalDate.parse(date);
        return conversionService.buscarPorFechaYDivisas(from, to, localDate);
    }

    @GetMapping("/historial/divisas")
    public List<Conversion> historial(
            @RequestParam String from,
            @RequestParam String to
    ) {
        return conversionService.historialDivisas(from, to);
    }

    @GetMapping("/historial/fecha")
    public List<Conversion> historialPorFecha(@RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date);
        return conversionService.historialPorFecha(localDate);
    }
}