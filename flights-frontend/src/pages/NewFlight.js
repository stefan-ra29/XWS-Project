import "./styles/NewFlight.css";
import { useEffect, useState } from "react";
import { getAllFlights, saveFlight } from "../services/FlightService";


export default function FlightManagment() {
    const [newFlight, setNewFlight] = useState({
        departure: "",
        departureDateTime: "",
        destination: "",
        arrivalDateTime: "",
        pricePerTicket: 0,
        availableSeats: 0,
      });

    const handleChange = (event) => {
        const name = event.target.name;
        const value = event.target.value;
        setNewFlight(values => ({...values, [name]: value}))
    }

    const handleSubmit = (event) => {
        event.preventDefault();
        alert(newFlight);
    }

    return (
    <>
    <div className="tbl-content">
        <h1>New Flight</h1>

        <div className="input-div">
            <label>Departure place</label>
            <br/>
            <input
                type="text"
                name="departure"
                value={newFlight.departure}
                onChange={handleChange}
            />
            <br/>
            <label>Departure time</label>
            <br/>
            <input
                type="datetime-local"
                name="departureDateTime"
                value={newFlight.departureDateTime}
                onChange={handleChange}
            />
            <br/>
            <label>Destination</label>
            <br/>
            <input
                type="text"
                name="destination"
                value={newFlight.destination}
                onChange={handleChange}

            />
            <br/>
            <label>Arrival time</label>
            <br/>
            <input
                type="datetime-local"
                name="arrivalDateTime"
                value={newFlight.arrivalDateTime}
                onChange={handleChange}
            />
            <br/>
            <label>Ticket price</label>
            <br/>
            <input
                type="number"
                name="pricePerTicket"
                value={newFlight.pricePerTicket}
                onChange={handleChange}
            />
            <br/>
            <label>Available seats</label>
            <br/>
            <input
                type="number"
                name="availableSeats"
                value={newFlight.availableSeats}
                onChange={handleChange}
            />
            <br/>
            <div className="btn-div">
                <a href="/flights"><button className="save-btn" onClick={() => saveFlight(newFlight)}>Save</button></a>
                <a href="/flights"><button className="cancel-btn">Cancel</button></a>
            </div>
        </div>
    </div>
    </>
  );
}
