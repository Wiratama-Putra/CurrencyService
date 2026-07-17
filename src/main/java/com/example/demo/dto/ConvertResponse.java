package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ConvertResponse {

    private BigDecimal amount;

    private String from;

    private String to;

    private BigDecimal rate;

    private BigDecimal result;

}
