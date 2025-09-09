package com.example.demo.service;

import com.example.demo.model.Currency;
import com.example.demo.repository.CurrencyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyService {
    private final CurrencyRepository repository;

    public CurrencyService(CurrencyRepository repository) {
        this.repository = repository;
    }

    public List<Currency> listar() {
        return repository.findAll();
    }

    public Currency guardar(Currency currency) {
        return repository.save(currency);
    }

    public Currency buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Currency actualizar(Long id, Currency currency) {
        Currency existente = buscarPorId(id);
        if (existente != null) {
            existente.setCode(currency.getCode());
            existente.setName(currency.getName());
            return repository.save(existente);
        }
        return null;
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
