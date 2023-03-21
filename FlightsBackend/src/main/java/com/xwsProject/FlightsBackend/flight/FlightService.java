package com.xwsProject.FlightsBackend.flight;

import java.util.List;

public interface FlightService {

    List<Flight> getAll();
    List<Flight> search(FlightSearchQueryDTO searchQuery);

    AvailablePlacesDTO getAvailablePlaces();
}
