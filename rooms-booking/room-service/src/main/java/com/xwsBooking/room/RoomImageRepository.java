package com.xwsBooking.room;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomImageRepository extends JpaRepository<RoomImage, Long> {
    List<RoomImage> findAllByRoom_Id(long roomId);
}
