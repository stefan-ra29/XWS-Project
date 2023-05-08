package com.xwsBooking.reservation.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private long id;
    private List<String> images;
}
