package com.xwsBooking.room;

import com.xwsBooking.availability.Availability;
import com.xwsBooking.price.Price;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "wifi", nullable = false)
    private boolean wifi;

    @Column(name = "free_parking", nullable = false)
    private boolean freeParking;

    @Column(name = "kitchen", nullable = false)
    private boolean kitchen;

    @Column(name = "air_conditioning", nullable = false)
    private boolean airConditioning;

    @Column(name = "min_number_of_guests", nullable = false)
    private int minNumberOfGuests;

    @Column(name = "max_number_of_guests", nullable = false)
    private int maxNumberOfGuests;

    @Column(name = "host_id", nullable = false)
    private long hostId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room")
    private List<Availability> availabilities;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room")
    private List<Price> prices;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "room")
    private List<RoomImage> images = new ArrayList<>();
}
