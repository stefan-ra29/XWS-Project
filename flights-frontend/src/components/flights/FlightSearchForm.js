import React from "react";
import "./FlightSearchForm.css";

export default function FlightsSearchForm({
  handleSubmit,
  searchQuery,
  handleInputChange,
  departures,
  destinations,
}) {
  const disablePastDate = () => {
    const today = new Date();
    const dd = String(today.getDate() + 1).padStart(2, "0");
    const mm = String(today.getMonth() + 1).padStart(2, "0"); //January is 0!
    const yyyy = today.getFullYear();
    return yyyy + "-" + mm + "-" + dd;
  };

  return (
    <form className="searchForm" onSubmit={handleSubmit}>
      <div className="departureSection">
        <label htmlFor="departurePlace">Depart from</label>
        <select
          name="departurePlace"
          id="departurePlace"
          value={searchQuery.departurePlace}
          onChange={handleInputChange}
        >
          {departures.map((departure) => {
            return (
              <option key={departure} value={departure}>
                {departure}
              </option>
            );
          })}
        </select>

        <label htmlFor="deartureDate">On</label>
        <input
          type="date"
          name="departureDate"
          id="departureDate"
          value={searchQuery.departureDate}
          onChange={handleInputChange}
          min={disablePastDate()}
        />
      </div>

      <div className="destinationSection">
        <label htmlFor="destination">Destination</label>
        <select
          name="destination"
          id="destination"
          value={searchQuery.destination}
          onChange={handleInputChange}
        >
          {destinations.map((destination) => {
            return (
              <option key={destination} value={destination}>
                {destination}
              </option>
            );
          })}
        </select>

        <label htmlFor="arrivalDate">On</label>
        <input
          type="date"
          name="arrivalDate"
          id="arrivalDate"
          value={searchQuery.arrivalDate}
          onChange={handleInputChange}
          min={disablePastDate()}
        />
      </div>

      <div className="ticketsSection">
        <label htmlFor="numberOfTickets">Number of tickets</label>
        <input
          className="ticketsInput"
          name="numberOfTickets"
          id="numberOfTickets"
          type="number"
          min="1"
          step="1"
          value={searchQuery.numberOfTickets}
          onChange={handleInputChange}
        />

        <button className="searchButton" type="submit">
          Search!
        </button>
      </div>

      <div className="buttonWrapper"></div>
    </form>
  );
}
