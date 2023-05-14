import React, { useEffect, useState } from 'react'
import { declineReservationRequest, acceptReservationRequest, getReservationRequestsByHost } from '../service/BookingService';
import './styles/GuestReservationRequests.css'

export default function HostReservationRequests() {

    const [reservationRequests, setReservationRequests] = useState([]);

    useEffect(() => {
        getReservationRequestsByHost(setReservationRequests);
    }, [])

  return (
    <>

    <h2 className='guestReservationRequestsHeader'>Your reservation requests</h2>
    
    {reservationRequests.length !== 0 && (
        <div className="rooms-wrapper">
          {reservationRequests.map((reservation) => (
                <div className='guestReservationRequestWrapper'>
                  <div>From: {reservation.fromDate}</div>
                  <div>To: {reservation.toDate}</div>
                  <div>Room: {reservation.roomName}</div>
                  <div>Number of guests: {reservation.numberOfGuests}</div>
                  <div>Location: {reservation.location}</div>
                  <div>Number of cancellation: {reservation.guestCancellationCount}</div>
                  <button className='decline-btn' onClick={() => {
                      declineReservationRequest(reservation.requestId);
                    }}>Decline</button>
                  <button className='accept-btn' onClick={() => {
                      acceptReservationRequest(reservation.requestId);
                    }}>Accept</button>

                </div>
          ))}
        </div>
      )}

    {reservationRequests.length === 0 && (
        <div className="rooms-wrapper">
          You currently don't have any reservation requests
        </div>
      )}

    </>
  )
}
