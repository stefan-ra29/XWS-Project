package com.xwsProject.FlightsBackend.flight;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightSearchResultDTO {
    private String id;
    private String departureDateTime;
    private String arrivalDateTime;
    private String departure;
    private String destination;
    private double pricePerTicket;
    private int availableSeats;

    public FlightSearchResultDTO(Flight flight) {
        this.id = flight.getId();
        this.departureDateTime = flight.getDepartureDateTime().toLocalDate().toString() + " " + flight.getDepartureDateTime().toLocalTime().toString();
        this.arrivalDateTime = flight.getArrivalDateTime().toLocalDate().toString() + " "  + flight.getArrivalDateTime().toLocalTime().toString();
        this.departure = flight.getDeparture();
        this.destination = flight.getDestination();
        this.pricePerTicket = flight.getPricePerTicket();
        this.availableSeats = flight.getAvailableSeats();
    }
}
