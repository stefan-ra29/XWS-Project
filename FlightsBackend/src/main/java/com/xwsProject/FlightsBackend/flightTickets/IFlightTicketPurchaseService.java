package com.xwsProject.FlightsBackend.flightTickets;

import java.util.List;

public interface IFlightTicketPurchaseService {
    boolean buy(FlightTicketPurchase madePurchase);
    List<PurchasedTicketWithFlight> getTickets(String userId);
}
