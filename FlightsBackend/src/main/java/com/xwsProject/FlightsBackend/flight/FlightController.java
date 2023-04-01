package com.xwsProject.FlightsBackend.flight;

import com.xwsProject.FlightsBackend.flight.dto.AvailablePlacesDTO;
import com.xwsProject.FlightsBackend.flight.dto.FlightSearchQueryDTO;
import com.xwsProject.FlightsBackend.flight.dto.FlightSearchResultDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/flight")
public class FlightController {

    private final IFlightService flightService;

    @GetMapping("/getAll")
    public List<FlightSearchResultDTO> getAll() {

        Flight f = Flight.builder().departureDateTime(LocalDateTime.now()).arrivalDateTime(LocalDateTime.now()).build();
        flightService.create(f);

        List<Flight> allFlights = flightService.getAll();
        List<FlightSearchResultDTO> allFlightsDTOs = new ArrayList<>();
        for (Flight flight : allFlights) {
            FlightSearchResultDTO dto = new FlightSearchResultDTO(flight);

            allFlightsDTOs.add(dto);
        }

        return allFlightsDTOs;
    }

    @PostMapping
    public ResponseEntity<String> createFlight() {
        Flight flight1 = Flight.builder().availableSeats(20).destination("prnjas").build();

        flightService.create(flight1);

        return new ResponseEntity(flight1, HttpStatus.CREATED);
    }

    @GetMapping("/available-places")
    public AvailablePlacesDTO getAvailablePlaces() {
        return flightService.getAvailablePlaces();
    }

    @PostMapping("/search")
    public ResponseEntity<List<FlightSearchResultDTO>> search(@RequestBody FlightSearchQueryDTO searchQuery) {
        List<Flight> flightsMatchingQuery = flightService.search(searchQuery);
        List<FlightSearchResultDTO> dtos = new ArrayList<>();

        for (Flight flight : flightsMatchingQuery) {
            FlightSearchResultDTO dto = new FlightSearchResultDTO(flight);
            dtos.add(dto);
        }

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
}
