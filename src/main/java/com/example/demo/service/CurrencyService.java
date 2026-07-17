package com.example.demo.service;

import com.example.demo.client.FrankfurterClient;
import com.example.demo.config.exception.CurrencyNotFoundException;
import com.example.demo.dto.ConvertRequest;
import com.example.demo.dto.ConvertResponse;
import com.example.demo.dto.ExchangeRateResponse;
import com.example.demo.logging.LoggingMarker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final FrankfurterClient frankfurterClient;

    public Map<String, String> getCurrencies() {

        long start = System.currentTimeMillis();

        log.info(LoggingMarker.BUSINESS, "Get currencies");

        try {

            Map<String, String> currencies =
                    frankfurterClient.getCurrencies();

            log.info(LoggingMarker.BUSINESS,
                    "Currencies loaded successfully. total={}",
                    currencies.size());

            return currencies;

        } finally {

            log.info(LoggingMarker.PERFORMANCE,
                    "getCurrencies elapsed={} ms",
                    System.currentTimeMillis() - start);

        }

    }

    public ExchangeRateResponse getLatestRate(String base) {

        long start = System.currentTimeMillis();

        log.info(LoggingMarker.BUSINESS,
                "Get latest exchange rate. base={}",
                base);

        try {

            return frankfurterClient.getLatest(base);

        } finally {

            log.info(LoggingMarker.PERFORMANCE,
                    "getLatestRate elapsed={} ms",
                    System.currentTimeMillis() - start);

        }

    }

    public ExchangeRateResponse getHistorical(
            LocalDate date,
            String base) {

        long start = System.currentTimeMillis();

        log.info(LoggingMarker.BUSINESS,
                "Get historical rate. date={} base={}",
                date,
                base);

        try {

            return frankfurterClient.getHistorical(date, base);

        } finally {

            log.info(LoggingMarker.PERFORMANCE,
                    "getHistorical elapsed={} ms",
                    System.currentTimeMillis() - start);

        }

    }

    public ConvertResponse getSpecificRate(
            String from,
            String to) {

        long start = System.currentTimeMillis();

        log.info(LoggingMarker.BUSINESS,
                "Get exchange rate {} -> {}",
                from,
                to);

        try {

            ExchangeRateResponse response =
                    frankfurterClient.convert(
                            BigDecimal.ONE,
                            from,
                            to);

            BigDecimal rate =
                    response.getRates().get(to);

            if (rate == null) {

                throw new CurrencyNotFoundException(
                        "Exchange rate not found for "
                                + from + " -> " + to);

            }

            log.info(LoggingMarker.BUSINESS,
                    "Rate {} -> {} = {}",
                    from,
                    to,
                    rate);

            return ConvertResponse.builder()
                    .amount(BigDecimal.ONE)
                    .from(from)
                    .to(to)
                    .rate(rate)
                    .result(rate)
                    .build();

        } catch (Exception ex) {

            log.error(LoggingMarker.ERROR,
                    "Failed getSpecificRate {} -> {}",
                    from,
                    to,
                    ex);

            throw ex;

        } finally {

            log.info(LoggingMarker.PERFORMANCE,
                    "getSpecificRate elapsed={} ms",
                    System.currentTimeMillis() - start);

        }

    }

    public ConvertResponse convert(ConvertRequest request) {

        long start = System.currentTimeMillis();

        log.info(LoggingMarker.BUSINESS,
                "Convert {} {} -> {}",
                request.getAmount(),
                request.getFrom(),
                request.getTo());

        try {

            ExchangeRateResponse response =
                    frankfurterClient.convert(
                            request.getAmount(),
                            request.getFrom(),
                            request.getTo());

            BigDecimal result =
                    response.getRates().get(request.getTo());

            if (result == null) {

                throw new CurrencyNotFoundException(
                        "Exchange rate not found for "
                                + request.getFrom()
                                + " -> "
                                + request.getTo());

            }

            BigDecimal rate =
                    result.divide(
                            request.getAmount(),
                            6,
                            RoundingMode.HALF_UP);

            ConvertResponse convertResponse =
                    ConvertResponse.builder()
                            .amount(request.getAmount())
                            .from(request.getFrom())
                            .to(request.getTo())
                            .rate(rate)
                            .result(result)
                            .build();

            log.info(LoggingMarker.BUSINESS,
                    "Conversion success result={}",
                    result);

            return convertResponse;

        } catch (Exception ex) {

            log.error(LoggingMarker.ERROR,
                    "Convert currency failed",
                    ex);

            throw ex;

        } finally {

            log.info(LoggingMarker.PERFORMANCE,
                    "convert elapsed={} ms",
                    System.currentTimeMillis() - start);

        }

    }

}