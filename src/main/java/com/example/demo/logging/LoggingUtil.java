package com.example.demo.logging;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Marker;

@Slf4j
@UtilityClass
public class LoggingUtil {

    private final ObjectMapper mapper = new ObjectMapper();

    public void request(Object request) {

        try {

            log.info(
                    (Marker) LoggingMarker.REQUEST,
                    mapper.writeValueAsString(request));

        } catch (Exception e) {

            log.info(
                    (Marker) LoggingMarker.REQUEST,
                    request.toString());

        }

    }

    public void response(Object response) {

        try {

            log.info(
                    (Marker) LoggingMarker.RESPONSE,
                    mapper.writeValueAsString(response));

        } catch (Exception e) {

            log.info(
                    (Marker) LoggingMarker.RESPONSE,
                    response.toString());

        }

    }

    public void business(String message, Object... args) {

        log.info(
                message,
                args,
                LoggingMarker.BUSINESS);

    }

    public void performance(long elapsed) {

        log.info(
                "Elapsed={} ms",
                elapsed,
                LoggingMarker.PERFORMANCE);

    }

    public void error(String message,
                      Throwable throwable) {

        log.error(
                message,
                throwable,
                LoggingMarker.ERROR);

    }

}
