package com.xwsBooking.reservation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRequestRepository extends JpaRepository<ReservationRequest, Long> {
    List<ReservationRequest> findAllByCustomerId(long customerId);
}
