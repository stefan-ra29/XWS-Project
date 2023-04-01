package com.xwsProject.FlightsBackend.flight;

import com.xwsProject.FlightsBackend.flight.dto.AvailablePlacesDTO;
import com.xwsProject.FlightsBackend.flight.dto.FlightSearchQueryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightService implements IFlightService {

    private final IFlightRepository flightRepository;
    @Override
    public List<Flight> getAll() {
        return flightRepository.findAll();
    }

    @Override
    public List<Flight> search(FlightSearchQueryDTO searchQuery) {
        List<Flight> allFlights = flightRepository.findAll();
        List<Flight> flightsMatchingQuery = new ArrayList<>();

        for(Flight flight : allFlights) {
            if(flight.getDeparture().equals(searchQuery.getDeparturePlace()) &&
               flight.getDestination().equals(searchQuery.getDestination()) &&
               flight.getArrivalDateTime().toLocalDate().toString().equals(searchQuery.getArrivalDate()) &&
               flight.getDepartureDateTime().toLocalDate().toString().equals(searchQuery.getDepartureDate()) &&
               flight.getAvailableSeats() >= searchQuery.getNumberOfTickets()) {
                flightsMatchingQuery.add(flight);
            }
        }

        return flightsMatchingQuery;
    }

    @Override
    public AvailablePlacesDTO getAvailablePlaces() {
        List<Flight> allFlights = flightRepository.findAll();
        List<String> departures = new ArrayList<>();
        List<String> destinations = new ArrayList<>();

        for(Flight flight : allFlights) {
            if(!departures.contains(flight.getDeparture())) {
                departures.add(flight.getDeparture());
            }
            if(!destinations.contains(flight.getDestination())) {
                destinations.add(flight.getDestination());
            }
        }

        return AvailablePlacesDTO.builder()
                                .destinations(destinations)
                                .departures(departures)
                                .build();
    }

    public boolean saveNewFlight(Flight flight){
        flightRepository.save(flight);
        return true;
    }
}
