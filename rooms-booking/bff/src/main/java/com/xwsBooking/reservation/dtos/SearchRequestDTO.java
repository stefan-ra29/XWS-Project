package com.xwsBooking.reservation.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SearchRequestDTO {
    private String location;
    private int numberOfGuests;
    private String dateFrom;
    private String dateTo;
}
