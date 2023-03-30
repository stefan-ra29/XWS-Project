import "./styles/FlightManagment.css";
import { useEffect, useState } from "react";
import { getAllFlights } from "../services/FlightService";


export default function FlightManagment() {
    const [flights, setFlights] = useState([]);

    useEffect(() => {
        getAllFlights(setFlights);
      }, []);

    return (
    <>
    <div className="tbl-content">
        <table class="table">
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
                {flights.map((flight) =>(
                    <tr className="flights-row">
                    <td>{flight.departure}</td>
                    <td>{flight.departureDateTime}</td>
                    <td>{flight.destination}</td>
                    <td>{flight.arrivalDateTime}</td>
                    <td>{flight.pricePerTicket}€</td>
                    <td>{flight.availableSeats}</td>
                    <td><button>Delete</button></td>
                </tr>
                ))}
            </tbody>
        </table>
    </div>
    </>
  );
}