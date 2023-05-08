package com.xwsBooking.reservation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRequestRepository extends JpaRepository<ReservationRequest, Long> {
}
