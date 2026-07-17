package com.example.demo.controller;

import com.example.demo.dto.ConvertRequest;
import com.example.demo.dto.ConvertResponse;
import com.example.demo.dto.ExchangeRateResponse;
import com.example.demo.logging.LoggingMarker;
import com.example.demo.service.CurrencyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.MarkerManager;
import org.slf4j.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;

    /**
     * GET /api/currencies
     */
    @GetMapping("/currencies")
    public Map<String, String> getCurrencies() {
        return currencyService.getCurrencies();
    }

    /**
     * GET /api/rates?base=USD
     */
    @GetMapping("/rates")
    public ExchangeRateResponse getLatestRate(
            @RequestParam(defaultValue = "USD") String base) {

        return currencyService.getLatestRate(base);
    }

    /**
     * GET /api/rates/USD/IDR
     */
    @GetMapping("/rates/{from}/{to}")
    public ConvertResponse getSpecificRate(
            @PathVariable String from,
            @PathVariable String to) {
        log.info((Marker) LoggingMarker.BUSINESS, "Get curren");
        return currencyService.getSpecificRate(from, to);
    }

    /**
     * POST /api/convert
     */
    @PostMapping("/convert")
    public ConvertResponse convert(
            @Valid @RequestBody ConvertRequest request) {

        return currencyService.convert(request);
    }

    /**
     * GET /api/history?date=2026-07-16&base=USD
     */
    @GetMapping("/history")
    public ExchangeRateResponse getHistoricalRate(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date,

            @RequestParam(defaultValue = "USD")
            String base) {

        return currencyService.getHistorical(date, base);
    }

}
