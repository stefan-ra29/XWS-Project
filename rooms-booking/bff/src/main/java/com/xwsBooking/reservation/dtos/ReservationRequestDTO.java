package com.xwsBooking.reservation.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ReservationRequestDTO {
    private long roomId;
    private String fromDate;
    private String toDate;
    private long guestId;
    private int numberOfGuests;
}
