package com.xwsBooking.room.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomAvailabilitiesDto {
    List<AvailabilityDto> availabilities;
}
