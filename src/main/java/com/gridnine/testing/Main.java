package com.gridnine.testing;

import java.util.List;

import static com.gridnine.testing.Filter.checkFlights;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();

        System.out.println("Вылет до текущего момента времени:");
        System.out.println(checkFlights(flights, Filter.departureBeforeNow()));
        System.out.println();
        System.out.println("Cегменты с датой прилёта раньше даты вылета:");
        System.out.println(checkFlights(flights, Filter.arrivalBeforeDeparture()));
        System.out.println();
        System.out.println("Общее время проведённое на земле превышает два часа:");
        System.out.println(checkFlights(flights, Filter.totalTransferTime()));

    }
}
