package com.xwsProject.FlightsBackend.flightTickets;

import com.xwsProject.FlightsBackend.flightTickets.dto.FlightTicketPurchaseDTO;
import com.xwsProject.FlightsBackend.flightTickets.modelMapper.FlightTicketPurchaseDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/flight_ticket_purchase")
public class FlightTicketPurchaseController {
    private final IFlightTicketPurchaseService flightTicketPurchaseService;
    private FlightTicketPurchaseDTOMapper flightTicketPurchaseDTOMapper = new FlightTicketPurchaseDTOMapper();

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
}
