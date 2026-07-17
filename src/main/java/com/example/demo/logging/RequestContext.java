package com.example.demo.logging;

import org.slf4j.MDC;

import java.util.UUID;

public final class RequestContext {

    private RequestContext() {
    }

    public static void init() {

        MDC.put(
                "traceId",
                UUID.randomUUID().toString());

        MDC.put(
                "requestId",
                UUID.randomUUID().toString());

    }

    public static void clear() {

        MDC.clear();

    }

}