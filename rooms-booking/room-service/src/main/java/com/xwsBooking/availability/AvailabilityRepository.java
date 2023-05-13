package com.xwsBooking.availability;

import com.xwsBooking.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
    List<Availability> findAvailabilitiesByRoom_Id(Long roomId);
    List<Availability> deleteAllByRoomId(long roomId);
}
