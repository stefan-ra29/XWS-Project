package com.xwsBooking.reservation;

import com.xwsBooking.reservation.dtos.ReservationRequestDTO;
import com.xwsBooking.reservation.dtos.ReservationRequestDisplayDTO;
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

    @GetMapping
    @RequestMapping("/available-locations")
    public List<String> getAvailableLocations() {
        List<String> availableLocations = reservationService.getAvailablePlaces();
        return availableLocations;
    }

    @RolesAllowed("ROLE_GUEST")
    @PostMapping
    @RequestMapping("/send-reservation-request")
    public boolean sendReservationRequest(@RequestBody ReservationRequestDTO reservationRequest) {
        return reservationService.sendReservationRequest(reservationRequest);
    }

    @RolesAllowed("ROLE_GUEST")
    @GetMapping
    @RequestMapping("/reservation-requests/{guestId}")
    public List<ReservationRequestDisplayDTO> getReservationRequestsByGuest(@PathVariable long guestId) {
        return reservationService.getReservationRequestsByGuest(guestId);
    }

    @RolesAllowed("ROLE_GUEST")
    @DeleteMapping
    @RequestMapping("/reservation-request/{requestId}")
    public void deleteReservationRequest(@PathVariable long requestId) {
        reservationService.deleteReservationRequest(requestId);
    }

    @GetMapping
    @RequestMapping("/reservation-exist/{guestId}")
    public boolean doesGuestHaveReservation(@PathVariable long guestId) {
        return reservationService.doesGuestHaveReservation(guestId);
    }
}
