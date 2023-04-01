import "./SingleTicketPurchase.css";

export default function SingleTicketPurchase({
  departureDate,
  arrivalDate,
  totalPrice,
  numberOfTickets,
  departure,
  destination,
  purchaseDate,
}) {
  return (
    <div className="ticket-purchase-wrapper">
      <div>
        <div className="info-section">
          <p>Depart from: </p>
          <p>{departure}</p>
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
          <p>Number of tickets: </p>
          <p>{numberOfTickets}</p>
        </div>
        <div className="info-section">
          <p>Total price: </p>
          <p>${totalPrice}</p>
        </div>
        <div className="info-section">
          <p>Purchase date: </p>
          <p>{purchaseDate}</p>
        </div>
      </div>
    </div>
  );
}
