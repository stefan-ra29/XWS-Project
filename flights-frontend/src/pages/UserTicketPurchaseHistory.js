import SingleTicketPurchase from "../components/userRelated/SingleTicketPurchase";
import { Link } from "react-router-dom";
import { useEffect, useState } from "react";
//import { searchFlights, getAvailablePlaces } from "../services/FlightService";
import "./styles/UserTicketPurchaseHistory.css";
//import FlightsSearchForm from "../components/flights/FlightSearchForm";

export default function UserTicketPurchaseHistory() {
  const [purchasedTickets, setPurchasedTickets] = useState([]);

  return (
    <>
      <h2 className="heading">This is your flight ticket purchase history!</h2>

      <div>
        {purchasedTickets.length !== 0 && (
          <div className="purchased-tickets-wrapper">
            {purchasedTickets.map((purchasedTicket) => {
              return (
                <SingleTicketPurchase
                  key={purchasedTicket.id}
                  departureDate={purchasedTicket.departureDateTime}
                  arrivalDate={purchasedTicket.arrivalDateTime}
                  totalPrice={purchasedTicket.totalPrice}
                  numberOfTickets={purchasedTicket.numberOfTickets}
                  departure={purchasedTicket.departure}
                  destination={purchasedTicket.destination}
                  purchaseDate={purchasedTicket.purchaseDate}
                />
              );
            })}
          </div>
        )}

        {purchasedTickets.length === 0 && (
          <div className="purchased-tickets-wrapper">
            <p className="no-result-text">You have no bought tickets!</p>
            <Link to="/browse">
              <div>Buy one here!</div>
            </Link>
          </div>
        )}
      </div>
    </>
  );
}
