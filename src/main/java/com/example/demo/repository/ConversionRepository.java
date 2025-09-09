package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Conversion;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

public interface ConversionRepository extends JpaRepository<Conversion, Long> {
    Optional<Conversion> findByFromCurrencyAndToCurrencyAndDate(String from, String to, LocalDate date);
    List<Conversion> findByFromCurrencyAndToCurrency(String from, String to);
    List<Conversion> findByDate(LocalDate date);
}