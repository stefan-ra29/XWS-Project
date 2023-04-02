package com.xwsProject.FlightsBackend.flightTickets.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BoughtTicketDTO {

    private String ticketId;
    private int numberOfTickets;
    private String purchaseDate;
    private int totalPrice;
    private String departureDate;
    private String arrivalDate;
    private String departure;
    private String destination;
}
