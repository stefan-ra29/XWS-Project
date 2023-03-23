package com.xwsProject.FlightsBackend.flightTickets.modelMapper;

import com.xwsProject.FlightsBackend.flightTickets.FlightTicketPurchase;
import com.xwsProject.FlightsBackend.flightTickets.dto.FlightTicketPurchaseDTO;

import java.time.LocalDateTime;

public class FlightTicketPurchaseDTOMapper {

    public FlightTicketPurchase toModel(FlightTicketPurchaseDTO purchaseDTO){
        return FlightTicketPurchase.builder()
                .flightId(purchaseDTO.getFlightId())
                .numberOfTickets(purchaseDTO.getNumberOfTickets())
                .purchaseDate(LocalDateTime.now())
                .customerId("0")
                .totalPrice(purchaseDTO.getTotalPrice())
                .build();
    }
}
