package com.example.demo.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Data
public class ExchangeRateResponse {

    private Double amount;

    private String base;

    private LocalDate date;

    private Map<String, BigDecimal> rates;

}
