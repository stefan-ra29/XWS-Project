package com.xwsProject.FlightsBackend.flight.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightSearchQueryDTO {
    private  String departureDate;
    private String arrivalDate;
    private String departurePlace;
    private String destination;
    private int numberOfTickets;
}
