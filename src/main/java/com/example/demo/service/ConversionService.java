package com.example.demo.service;

import com.example.demo.model.Conversion;
import com.example.demo.repository.ConversionRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ConversionService {

    @Autowired
    private ConversionRepository conversionRepository;

    @Value("${api.exchangerate.url}")
    private String apiUrl;

    @Value("${api.exchangerate.key:}") // opcional, si no tienes key no truena
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public Conversion convertirYGuardar(String from, String to) {
        LocalDate today = LocalDate.now();
        String API_URL = apiUrl +"/"+ apiKey + "/pair/"+from+"/"+to;

        return conversionRepository.findByFromCurrencyAndToCurrencyAndDate(from, to, today)
                .orElseGet(() -> {
                    Map<String, Object> response = restTemplate.getForObject(API_URL, Map.class);
                    double rate = ((Number) response.get("conversion_rate")).doubleValue();

                    Conversion conversion = new Conversion();
                    conversion.setFromCurrency(from);
                    conversion.setToCurrency(to);
                    conversion.setRate(rate);
                    conversion.setDate(today);
                    conversion.setTimestamp(LocalDateTime.now());

                    return conversionRepository.save(conversion);
                });
    }

    public java.util.List<Conversion> obtenerHistorial() {
        return conversionRepository.findAll();
    }

    public Optional<Conversion> buscarPorFechaYDivisas(String from, String to, LocalDate date) {
        return conversionRepository.findByFromCurrencyAndToCurrencyAndDate(from, to, date);
    }

    public List<Conversion> historialDivisas(String from, String to) {
        return conversionRepository.findByFromCurrencyAndToCurrency(from, to);
    }

    public List<Conversion> historialPorFecha(LocalDate date) {
        return conversionRepository.findByDate(date);
    }

}
