package com.xwsBooking.availability;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
    List<Availability> findAvailabilitiesByRoom_Id(Long roomId);

    List<Availability> deleteAllByRoomId(long roomId);

    @Query(value = "SELECT * FROM availabilities a " +
            "WHERE a.to_date >= :fromDate AND a.from_date <= :toDate", nativeQuery = true)
    List<Availability> findAvailableByFromDateAndToDate(@Param("fromDate") LocalDate fromDate,
                                     @Param("toDate") LocalDate toDate);
}
