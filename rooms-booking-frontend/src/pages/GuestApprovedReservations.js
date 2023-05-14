import React, { useEffect, useState } from 'react'
import { getApprovedReservationsByGuest, cancelApprovedReservation } from '../service/BookingService';
import './styles/GuestReservationRequests.css'

export default function GuestApprovedReservations() {

    const [approvedReservations, setApprovedReservations] = useState([]);

    useEffect(() => {
        getApprovedReservationsByGuest(setApprovedReservations);
    }, [])

  return (
    <>

    <h2 className='guestReservationRequestsHeader'>Your approved reservations</h2>
    
    {approvedReservations.length !== 0 && (
        <div className="rooms-wrapper">
          {approvedReservations.map((reservation) => (
                <div className='guestReservationRequestWrapper'>
                  <div>From: {reservation.fromDate}</div>
                  <div>To: {reservation.toDate}</div>
                  <div>Room: {reservation.roomName}</div>
                  <div>Number of guests: {reservation.numberOfGuests}</div>
                  <div>Location: {reservation.location}</div>
                  {new Date(reservation.fromDate) > new Date() && (
                    <button onClick={() => {
                        cancelApprovedReservation(reservation.reservationId);
                        /*
                        const modifiedReservations = approvedReservations.filter(
                          (f) => f.reservationId !== reservation.reservationId
                        );
                        setApprovedReservations(modifiedReservations);
                        */
                      }}>Cancel reservation</button>
                  )}
                </div>
          ))}
        </div>
      )}

    {approvedReservations.length === 0 && (
        <div className="rooms-wrapper">
          You currently don't have any approved reservation
        </div>
      )}

    </>
  )
}
