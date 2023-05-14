package com.xwsBooking.reservation;

import com.xwsBooking.reservation.dtos.*;
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

    @RolesAllowed("ROLE_GUEST")
    @GetMapping
    @RequestMapping("/approved-reservations/{guestId}")
    public List<ApprovedReservationDTO> getApprovedReservationForGuest(@PathVariable long guestId) {
        return reservationService.getApprovedReservationForGuest(guestId);
    }

    @RolesAllowed("ROLE_GUEST")
    @DeleteMapping
    @RequestMapping("/cancel-reservation/{reservationId}")
    public void cancelApprovedReservation(@PathVariable long reservationId) {
        reservationService.cancelApprovedReservation(reservationId);
    }

    @RolesAllowed("ROLE_HOST")
    @DeleteMapping
    @RequestMapping("/host-requests/{hostId}")
    public List<HostReservationRequestsDisplayDTO> getAllReservationRequestsForHost(@PathVariable long hostId) {
        return reservationService.getAllReservationRequestsForHost(hostId);
    }

    @RolesAllowed("ROLE_HOST")
    @DeleteMapping
    @RequestMapping("/decline-reservation/{reservationId}")
    public void declineReservationRequest(@PathVariable long reservationId) {
        reservationService.declineReservationRequest(reservationId);
    }

    @RolesAllowed("ROLE_HOST")
    @GetMapping
    @RequestMapping("/approve-reservation/{reservationId}")
    public void approveReservationRequest(@PathVariable long reservationId) {
        reservationService.approveReservationRequest(reservationId);
    }
}
