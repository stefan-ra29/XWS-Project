package com.xwsProject.FlightsBackend.flight;

import com.xwsProject.FlightsBackend.flight.dto.AvailablePlacesDTO;
import com.xwsProject.FlightsBackend.flight.dto.FlightSearchQueryDTO;

import java.util.List;

public interface FlightService {

    List<Flight> getAll();
    List<Flight> search(FlightSearchQueryDTO searchQuery);

    AvailablePlacesDTO getAvailablePlaces();
}
