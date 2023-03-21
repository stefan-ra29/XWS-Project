package com.xwsProject.FlightsBackend.flight;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "flights")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flight{
    @Id
    private String id;
    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;
    private String departure;
    private String destination;
    private double pricePerTicket;
    private int availableSeats;
}
