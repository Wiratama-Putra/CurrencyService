package com.example.demo.logging;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public final class LoggingMarker {

    private LoggingMarker() {
    }

    public static final Marker REQUEST =
            MarkerFactory.getMarker("REQUEST");

    public static final Marker RESPONSE =
            MarkerFactory.getMarker("RESPONSE");

    public static final Marker BUSINESS =
            MarkerFactory.getMarker("BUSINESS");

    public static final Marker PERFORMANCE =
            MarkerFactory.getMarker("PERFORMANCE");

    public static final Marker ERROR =
            MarkerFactory.getMarker("ERROR");

}
