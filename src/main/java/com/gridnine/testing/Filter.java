package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Filter {
    static List<Flight> checkFlights(List<Flight> flights, Predicate<Flight> p) {
        return flights.stream().filter(p).collect(Collectors.toList());
    }

    static Predicate<Flight> departureBeforeNow() {
        return flight -> flight.getSegments().stream().allMatch(segment -> segment.getDepartureDate().isBefore(LocalDateTime.now()));
    }

    static Predicate<Flight> arrivalBeforeDeparture() {
        return flight -> flight.getSegments().stream().allMatch(segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate()));
    }

    static Predicate<Flight> totalTransferTime() {
        return flight -> {
            List<Segment> segments = flight.getSegments();
            long sumTransferTime = 0;

            for (int i = 0; i < segments.size() - 1; ++i) {
                LocalDateTime arrivalTime = segments.get(i).getArrivalDate();
                LocalDateTime departureTime = segments.get(i + 1).getDepartureDate();
                sumTransferTime = Duration.between(arrivalTime, departureTime).toHours();
            }

            return sumTransferTime >= 2;

        };
    }
}
