package com.xwsBooking.reservation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuestCancellationRepository extends JpaRepository<GuestCancellation, Long> {
    List<GuestCancellation> findAllByCustomerId(long customerId);
}
