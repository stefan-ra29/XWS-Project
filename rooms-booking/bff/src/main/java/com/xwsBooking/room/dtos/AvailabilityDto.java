package com.xwsBooking.room.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AvailabilityDto {
    long id;
    long roomId;
    LocalDate from;
    LocalDate to;
    long hostId;
}
