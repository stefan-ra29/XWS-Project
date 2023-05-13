package com.xwsBooking.reservation.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ReservationRequestDisplayDTO {
    private long requestId;
    private String fromDate;
    private String toDate;
    private String roomName;
    private int numberOfGuests;
    private String location;
}
