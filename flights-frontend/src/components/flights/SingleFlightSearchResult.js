import "./SingleFlightSearchResult.css";
import { buyTickets } from "../../services/FlightTicketService";

export default function SingleFlightSearchResult({
  flightId,
  departureDate,
  arrivalDate,
  pricePerTicket,
  numberOfTickets,
  departurePlace,
  destination,
  onBuyResetHandler
}) {
  const totalPrice = parseInt(pricePerTicket) * parseInt(numberOfTickets);

  return (
    <div className="flight-wrapper">
      <div>
        <div className="info-section">
          <p>Depart from: </p>
          <p>{departurePlace}</p>
        </div>
        <div className="info-section">
          <p>On: </p>
          <p>{departureDate}</p>
        </div>
        <div className="info-section">
          <p>Destination: </p>
          <p>{destination}</p>
        </div>
        <div className="info-section">
          <p>On: </p>
          <p>{arrivalDate}</p>
        </div>
        <div className="info-section">
          <p>Price per ticket: </p>
          <p>${pricePerTicket}</p>
        </div>
        <div className="info-section">
          <p>Total price for {numberOfTickets} tickets: </p>
          <p>${totalPrice}</p>
        </div>
      </div>
      {localStorage.getItem("token") != null &&
      <div className="buyButtonWrapper">
        <button
          className="buyButton"
          onClick={buyTickets.bind(this, flightId, numberOfTickets, totalPrice, onBuyResetHandler)}
        >
          Buy {numberOfTickets} tickets!
        </button>
      </div>
      }
    </div>
  );
}
