package com.xwsProject.FlightsBackend.flightTickets;

import com.xwsProject.FlightsBackend.flight.Flight;
import com.xwsProject.FlightsBackend.flightTickets.dto.BoughtTicketDTO;
import com.xwsProject.FlightsBackend.flightTickets.dto.FlightTicketPurchaseDTO;
import com.xwsProject.FlightsBackend.flightTickets.modelMapper.BoughtTicketDTOMapper;
import com.xwsProject.FlightsBackend.flightTickets.modelMapper.FlightTicketPurchaseDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/flight_ticket_purchase")
public class FlightTicketPurchaseController {
    private final IFlightTicketPurchaseService flightTicketPurchaseService;
    private FlightTicketPurchaseDTOMapper flightTicketPurchaseDTOMapper = new FlightTicketPurchaseDTOMapper();
    private BoughtTicketDTOMapper boughtTicketDTOMapper = new BoughtTicketDTOMapper();

    @PostMapping("/buy")
    public ResponseEntity<String> buy(@RequestBody FlightTicketPurchaseDTO madePurchase) {
        try{
            flightTicketPurchaseService.buy(flightTicketPurchaseDTOMapper.toModel(madePurchase));
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception e){
            System.out.println("Error occured: " + e.toString());
            return new ResponseEntity("Error ocurred", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getByUser")
    public ResponseEntity<List<BoughtTicketDTO>> getTickets(@RequestParam String userId) {
        try{
            List<PurchasedTicketWithFlight> tickets = flightTicketPurchaseService.getTickets(userId);
            return new ResponseEntity(boughtTicketDTOMapper.toDTOList(tickets), HttpStatus.OK);
        }catch(Exception e){
            System.out.println("Error occured: " + e.toString());
            return new ResponseEntity("Error ocurred", HttpStatus.BAD_REQUEST);
        }
    }
}
