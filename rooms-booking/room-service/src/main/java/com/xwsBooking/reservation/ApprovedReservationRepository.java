package com.xwsBooking.reservation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApprovedReservationRepository extends JpaRepository<ApprovedReservation, Long> {
    List<ApprovedReservation> findAllByCustomerId(long customerId);
    List<ApprovedReservation> deleteAllByRoomId(long roomId);
}
