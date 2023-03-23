package com.xwsProject.FlightsBackend.flightTickets.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FlightTicketPurchaseDTO {

    private String flightId;
    private int numberOfTickets;
    private String customerId;
    private int totalPrice;
}
