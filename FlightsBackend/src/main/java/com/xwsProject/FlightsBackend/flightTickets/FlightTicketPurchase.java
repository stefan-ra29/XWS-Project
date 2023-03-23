package com.xwsProject.FlightsBackend.flightTickets;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "flight_ticket_purchases")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightTicketPurchase {
    @Id
    private String id;
    private String flightId;
    private String customerId;
    private int numberOfTickets;
    private LocalDateTime purchaseDate;
}
