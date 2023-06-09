import React from "react";
import { useState, useEffect } from "react";
import RoomSearchForm from "../components/booking/RoomSearchForm";
import { searchRooms, getAvailableLocations } from "../service/BookingService";
import RoomSearchResult from "../components/booking/RoomSearchResult";
import "./styles/Booking.css";
import { getRoleFromLocalStorage } from "../utils/LocalStorageService";
import { useNavigate } from "react-router-dom";

export default function Booking() {
  const [rooms, setRooms] = useState([]);
  const [availableLocations, setAvailableLocations] = useState([]);

  const [searchQuery, setSearchQuery] = useState({
    location: "",
    dateFrom: "",
    dateTo: "",
    numberOfGuests: 1,
  });

  useEffect(() => {
    getAvailableLocations(setAvailableLocations);
  }, []);

  useEffect(() => {
    setSearchQuery((prevState) => ({
      ...prevState,
      location: availableLocations[0]
    })) 
  }, [availableLocations]);

  const role = getRoleFromLocalStorage();
  const navigate = useNavigate();
  useEffect(() => {
    if (role !== "GUEST") {
      navigate("/");
    }
  }, []);

  function handleInputChange(event) {
    const { name, value } = event.target;
    setSearchQuery((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  }

  function handleSubmit(event) {
    event.preventDefault();
    searchRooms(searchQuery, setRooms);
  }

  return (
    <>
      <RoomSearchForm
        handleSubmit={handleSubmit}
        searchQuery={searchQuery}
        handleInputChange={handleInputChange}
        availableLocations={availableLocations}
      />

      {rooms.length !== 0 && (
        <div className="rooms-wrapper">
          {rooms.map((room) => {
            return (
              <RoomSearchResult
                key={room.name}
                roomId={room.id}
                location={room.location}
                name={room.name}
                hasAC={room.hasAC}
                hasFreeParking={room.hasFreeParking}
                hasWifi={room.hasWifi}
                hasKitchen={room.hasKitchen}
                minGuests={room.minGuests}
                maxGuests={room.maxGuests}
                totalPrice={room.totalPrice}
                pricePerDay={room.pricePerDay}
                images={room.images}
                fromDate={searchQuery.dateFrom}
                toDate={searchQuery.dateTo}
                numberOfGuests={searchQuery.numberOfGuests}
              />
            );
          })}
        </div>
      )}

      {rooms.length === 0 && (
        <div className="rooms-wrapper">
          <p className="no-result-text">
            No rooms match your search criteria :( Try something else
          </p>
        </div>
      )}
    </>
  );
}
