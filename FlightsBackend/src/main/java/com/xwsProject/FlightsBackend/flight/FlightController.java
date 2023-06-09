package com.xwsProject.FlightsBackend.flight;

import com.xwsProject.FlightsBackend.flight.dto.AvailablePlacesDTO;
import com.xwsProject.FlightsBackend.flight.dto.FlightSearchQueryDTO;
import com.xwsProject.FlightsBackend.flight.dto.FlightSearchResultDTO;
import com.xwsProject.FlightsBackend.flightTickets.dto.FlightTicketPurchaseDTO;
import jakarta.annotation.security.RolesAllowed;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ResponseEntity<List<FlightSearchResultDTO>> search(@RequestBody FlightSearchQueryDTO searchQuery) {
        List<Flight> flightsMatchingQuery = flightService.search(searchQuery);
        List<FlightSearchResultDTO> dtos = new ArrayList<>();

        for (Flight flight : flightsMatchingQuery) {
            FlightSearchResultDTO dto = new FlightSearchResultDTO(flight);
            dtos.add(dto);
        }

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @RolesAllowed("ROLE_ADMINISTRATOR")
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Flight flight) {
        try{
            flightService.saveNewFlight(flight);
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity("Error", HttpStatus.BAD_REQUEST);
        }
    }

    @RolesAllowed("ROLE_ADMINISTRATOR")
    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@PathParam("id") String id) {
        try{
            flightService.deleteFlight(id);
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity("Error", HttpStatus.BAD_REQUEST);
        }
    }
}
