import React from 'react'
import './GuestReservationRequest.css'
import { deleteGuestReservationRequest } from '../../service/BookingService'


export default function GuestReservationRequest({requestId, fromDate, toDate, roomName, numberOfGuests, location}) {

    function handleDeletion() {
        deleteGuestReservationRequest(requestId);
    }

  return (
    <div className='guestReservationRequestWrapper'>
        <div>From: {fromDate}</div>
        <div>To: {toDate}</div>
        <div>Room: {roomName}</div>
        <div>Number of guests: {numberOfGuests}</div>
        <div>Location: {location}</div>
        <button onClick={handleDeletion}>Cancel this reservation</button>
    </div>
  )
}
