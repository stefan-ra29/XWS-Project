package com.xwsBooking.price;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PriceRepository extends JpaRepository<Price, Long> {

    List<Price> findPricesByRoom_Id(Long roomId);
    List<Price> deleteAllByRoomId(long roomId);
}
