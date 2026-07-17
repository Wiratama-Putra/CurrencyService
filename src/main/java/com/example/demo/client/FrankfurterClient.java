package com.example.demo.client;

import com.example.demo.config.exception.CurrencyNotFoundException;
import com.example.demo.config.exception.ExternalApiException;
import com.example.demo.dto.ExchangeRateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class FrankfurterClient {

    private final RestClient restClient;

    /**
     * GET /currencies
     */
    public Map<String, String> getCurrencies() {

        return restClient.get()
                .uri("/currencies")
                .retrieve()
                .body(Map.class);
    }

    /**
     * GET /latest?base=USD
     */
    public ExchangeRateResponse getLatest(String base) {

        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/latest")
                        .queryParam("base", base)
                        .build())
                .retrieve()
                .body(ExchangeRateResponse.class);
    }

    /**
     * GET /latest?amount=100&base=USD&symbols=IDR
     */
    public ExchangeRateResponse convert(
            BigDecimal amount,
            String from,
            String to) {

        try {

            return restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/latest")
                            .queryParam("amount", amount)
                            .queryParam("base", from)
                            .queryParam("symbols", to)
                            .build())
                    .retrieve()
                    .body(ExchangeRateResponse.class);

        } catch (HttpClientErrorException.NotFound ex) {

            throw new CurrencyNotFoundException(
                    "Currency " + from + " or " + to + " does not exist.");

        } catch (HttpClientErrorException ex) {

            throw new ExternalApiException(
                    "Failed to retrieve exchange rate: " + ex.getStatusCode());
        }
    }

    /**
     * GET /2026-07-01?base=USD
     */
    public ExchangeRateResponse getHistorical(
            LocalDate date,
            String base) {

        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/" + date)
                        .queryParam("base", base)
                        .build())
                .retrieve()
                .body(ExchangeRateResponse.class);
    }

}
