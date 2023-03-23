package com.xwsProject.FlightsBackend.flightTickets;

import com.xwsProject.FlightsBackend.flight.Flight;
import com.xwsProject.FlightsBackend.flight.IFlightRepository;
import com.xwsProject.FlightsBackend.flightTickets.FlightTicketPurchase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlightTicketPurchaseService implements IFlightTicketPurchaseService {

    private final IFlightTicketPurchaseRepository flightTicketPurchaseRepository;
    private final IFlightRepository flightRepository;

    @Override
    public boolean buy(FlightTicketPurchase madePurchase) {
        try {
            Optional<Flight> flight = flightRepository.findById(madePurchase.getFlightId());
            if (flight.isPresent()) {
                if (flight.get().getAvailableSeats() >= madePurchase.getNumberOfTickets()) {
                    flight.get().setAvailableSeats(flight.get().getAvailableSeats() - madePurchase.getNumberOfTickets());
                    flightRepository.save(flight.get());
                    flightTicketPurchaseRepository.save(madePurchase);
                } else {
                    throw new RuntimeException("Unavailable number of tickets.");
                }
                return true;
            } else {
                throw new RuntimeException("There's no flight with that id.");
            }
        }catch (Exception e){
            throw new RuntimeException("Can't save to database.");
        }
    }
}
