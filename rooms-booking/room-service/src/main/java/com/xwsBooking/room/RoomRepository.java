package com.xwsBooking.room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findById(long id);
    List<Room> findRoomsByLocation(String location);

    @Query("SELECT DISTINCT r.location FROM rooms r")
    List<String> findDistinctLocations();

    List<Room> deleteAllByHostId(long hostId);

    List<Room> findAllByHostId(long hostId);

}
