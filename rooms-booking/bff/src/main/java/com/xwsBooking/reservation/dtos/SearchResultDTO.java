package com.xwsBooking.reservation.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SearchResultDTO {
    private String location;
    private String name;
    private double pricePerDay;
    private double totalPrice;
    private boolean hasAC;
    private boolean hasFreeParking;
    private boolean hasKitchen;
    private boolean hasWifi;
    private int minGuests;
    private int maxGuests;
}
