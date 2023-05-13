package com.xwsBooking.reservation;

import com.xwsBooking.room.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "approved_reservations")
public class ApprovedReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private long customerId;
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
    private int numberOfGuests;
    private LocalDate fromDate;
    private LocalDate toDate;
}
