package com.example.demo.controller;

import com.example.demo.model.Currency;
import com.example.demo.service.CurrencyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/divisas")
public class CurrencyController {
    private final CurrencyService service;

    public CurrencyController(CurrencyService service) {
        this.service = service;
    }

    @GetMapping
    public List<Currency> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Currency obtener(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public Currency crear(@RequestBody Currency currency) {
        return service.guardar(currency);
    }

    @PutMapping("/{id}")
    public Currency actualizar(@PathVariable Long id, @RequestBody Currency currency) {
        return service.actualizar(id, currency);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
