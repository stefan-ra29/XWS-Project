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
public class RoomDto {
    long id;
    String name;
    String location;
    boolean airConditioning;
    boolean wifi;
    boolean kitchen;
    boolean freeParking;
    int minNumberOfGuests;
    int maxNumberOfGuests;
    List<String> images;
    long hostId;
    boolean automaticReservationConfirmation;
}
