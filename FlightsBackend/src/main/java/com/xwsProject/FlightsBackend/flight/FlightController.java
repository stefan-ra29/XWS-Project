package com.xwsProject.FlightsBackend.flight;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
