package com.xwsProject.FlightsBackend.flightTickets;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFlightTicketPurchaseRepository extends MongoRepository<FlightTicketPurchase, String> {

}
