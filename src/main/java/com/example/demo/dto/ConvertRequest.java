package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ConvertRequest {

    @Positive
    private BigDecimal amount;

    @NotBlank
    private String from;

    @NotBlank
    private String to;

}