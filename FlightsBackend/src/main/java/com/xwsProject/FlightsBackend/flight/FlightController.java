package com.xwsProject.FlightsBackend.flight;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/flight")
public class FlightController {

    private final FlightService flightService;

    @GetMapping("/getAll")
    public List<FlightSearchResultDTO> getAll() {
        List<Flight> allFlights = flightService.getAll();
        List<FlightSearchResultDTO> allFlightsDTOs = new ArrayList<>();
        for (Flight flight : allFlights) {
            FlightSearchResultDTO dto = new FlightSearchResultDTO(flight);

            allFlightsDTOs.add(dto);
        }

        return allFlightsDTOs;
    }

    @GetMapping("/available-places")
    public AvailablePlacesDTO getAvailablePlaces() {
        return flightService.getAvailablePlaces();
    }

    @PostMapping("/search")
    public List<FlightSearchResultDTO> search(@RequestBody FlightSearchQueryDTO searchQuery) {
        List<Flight> flightsMatchingQuery = flightService.search(searchQuery);
        List<FlightSearchResultDTO> dtos = new ArrayList<>();

        for (Flight flight : flightsMatchingQuery) {
            FlightSearchResultDTO dto = new FlightSearchResultDTO(flight);

            dtos.add(dto);
        }

        return dtos;
    }

}
