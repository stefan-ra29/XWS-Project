package com.xwsBooking.reservation;

import com.xwsBooking.reservation.dtos.ReservationRequestDTO;
import com.xwsBooking.reservation.dtos.SearchRequestDTO;
import com.xwsBooking.reservation.dtos.SearchResultDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping
    @RequestMapping("/search")
    public List<SearchResultDTO> search(@RequestBody SearchRequestDTO searchRequest) {
        return reservationService.search(searchRequest);
    }

    @RolesAllowed("ROLE_GUEST")
    @PostMapping
    @RequestMapping("/send-reservation-request")
    public boolean sendReservationRequest(@RequestBody ReservationRequestDTO reservationRequest) {
        return reservationService.sendReservationRequest(reservationRequest);
    }

}
