import "./styles/FlightManagment.css";
import { useEffect, useState } from "react";
import { getAllFlights, deleteFlight } from "../services/FlightService";
import { getRoleFromLocalStorage } from "../utils/LocalStorageService";

export default function FlightManagment() {
  const [flights, setFlights] = useState([]);
  const role = getRoleFromLocalStorage();
  useEffect(() => {
    if (role != "ADMINISTRATOR") {
      window.location.replace("/");
    }
    getAllFlights(setFlights);
  }, []);

  return (
    <>
      <div className="tbl-content">
        <h1>Flight Managment</h1>
        <table className="table">
          <thead>
            <tr>
              <th>Departure</th>
              <th>Time</th>
              <th>Destination</th>
              <th>Time</th>
              <th>Ticket Price</th>
              <th>Remaining Tickets</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            {flights.map((flight) => (
              <tr className="flights-row">
                <td>{flight.departure}</td>
                <td>{flight.departureDateTime}</td>
                <td>{flight.destination}</td>
                <td>{flight.arrivalDateTime}</td>
                <td>{flight.pricePerTicket}€</td>
                <td>{flight.availableSeats}</td>
                <td>
                  <button
                    className="delete-btn"
                    onClick={() => {
                      deleteFlight(flight.id);
                      const modifiedFlights = flights.filter(
                        (f) => f.id !== flight.id
                      );
                      setFlights(modifiedFlights);
                    }}
                  >
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
        <a href="/new-flight">
          <button className="new-flight-btn">New flight</button>
        </a>
      </div>
    </>
  );
}
