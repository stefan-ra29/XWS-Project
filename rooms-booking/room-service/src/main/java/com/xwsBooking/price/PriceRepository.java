package com.xwsBooking.price;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PriceRepository extends JpaRepository<Price, Long> {

    List<Price> findPricesByRoom_Id(Long roomId);

    List<Price> deleteAllByRoomId(long roomId);

    @Query(value = "SELECT * FROM prices a " +
            "WHERE a.to_date >= :fromDate AND a.from_date <= :toDate AND a.room_id = :roomId", nativeQuery = true)
    List<Price> findAvailableByFromDateAndToDate(@Param("fromDate") LocalDate fromDate,
                                                        @Param("toDate") LocalDate toDate, @Param("roomId") long roomId);

}
