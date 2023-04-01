package com.xwsProject.FlightsBackend.flightTickets;

import com.xwsProject.FlightsBackend.flight.Flight;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchasedTicketWithFlight {

    private Flight flight;
    private FlightTicketPurchase ticket;
}
