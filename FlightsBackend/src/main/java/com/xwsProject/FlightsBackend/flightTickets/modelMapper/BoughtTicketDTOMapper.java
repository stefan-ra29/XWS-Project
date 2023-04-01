package com.xwsProject.FlightsBackend.flightTickets.modelMapper;

import com.xwsProject.FlightsBackend.flight.Flight;
import com.xwsProject.FlightsBackend.flight.IFlightService;
import com.xwsProject.FlightsBackend.flightTickets.FlightTicketPurchase;
import com.xwsProject.FlightsBackend.flightTickets.IFlightTicketPurchaseService;
import com.xwsProject.FlightsBackend.flightTickets.PurchasedTicketWithFlight;
import com.xwsProject.FlightsBackend.flightTickets.dto.BoughtTicketDTO;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BoughtTicketDTOMapper {

    final static DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm");
    public List<BoughtTicketDTO> toDTOList(List<PurchasedTicketWithFlight> tickets){

        List<BoughtTicketDTO> boughtTicketDTOS = new ArrayList<>();
        for ( PurchasedTicketWithFlight ticket: tickets
             ) {
            boughtTicketDTOS.add(toDTO(ticket));
        }
        return boughtTicketDTOS;
    }

    public BoughtTicketDTO toDTO(PurchasedTicketWithFlight ticket){
        return BoughtTicketDTO.builder()
                .numberOfTickets(ticket.getTicket().getNumberOfTickets())
                .purchaseDate(ticket.getTicket().getPurchaseDate().format(CUSTOM_FORMATTER))
                .totalPrice(ticket.getTicket().getTotalPrice())
                .departureDate(ticket.getFlight().getDepartureDateTime().format(CUSTOM_FORMATTER))
                .arrivalDate(ticket.getFlight().getArrivalDateTime().format(CUSTOM_FORMATTER))
                .departure(ticket.getFlight().getDeparture())
                .destination(ticket.getFlight().getDestination())
                .ticketId(ticket.getTicket().getId())
                .build();
    }
}
