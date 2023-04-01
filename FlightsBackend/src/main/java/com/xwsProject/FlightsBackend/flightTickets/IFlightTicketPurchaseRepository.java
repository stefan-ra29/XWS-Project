package com.xwsProject.FlightsBackend.flightTickets;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFlightTicketPurchaseRepository extends MongoRepository<FlightTicketPurchase, String> {

    List<FlightTicketPurchase> findAllByCustomerId(String userId);
}
