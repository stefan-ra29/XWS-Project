package com.xwsBooking.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ApprovedReservationRepository extends JpaRepository<ApprovedReservation, Long> {
    List<ApprovedReservation> findAllByCustomerId(long customerId);
    List<ApprovedReservation> deleteAllByRoomId(long roomId);

    @Query(value = "SELECT * FROM approved_reservations a " +
            "WHERE a.to_date >= :fromDate AND a.from_date <= :toDate AND a.room_id = :roomId", nativeQuery = true)
    List<ApprovedReservation> findAllByFromDateAndToDateAndRoom(@Param("fromDate") LocalDate fromDate,
                                                 @Param("toDate") LocalDate toDate, @Param("roomId") long roomId);
}
