package com.xwsBooking.reservation;

import com.xwsBooking.reservation.dtos.SearchRequestDTO;
import com.xwsBooking.reservation.dtos.SearchResultDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
