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
@Entity(name = "guest_cancellation")
public class GuestCancellation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private long customerId;
    private int cancellationCount;
}
