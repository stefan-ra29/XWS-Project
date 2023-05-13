import React, { useEffect, useState } from 'react'
import { getReservationRequestsByGuest } from '../service/BookingService';
import GuestReservationRequest from '../components/booking/GuestReservationRequest';
import './styles/GuestReservationRequests.css'

export default function GuestReservationRequests() {

    const [reservationRequests, setReservationRequests] = useState([]);

    useEffect(() => {
        getReservationRequestsByGuest(setReservationRequests);
    }, [])
 
  return (
    <>

    <h2 className='guestReservationRequestsHeader'>Your reservation requests</h2>
    
    {reservationRequests.length !== 0 && (
        <div className="rooms-wrapper">
          {reservationRequests.map((reservationRequest) => {
            return (
              <GuestReservationRequest
                key={reservationRequest.requestId}
                requestId={reservationRequest.requestId}
                fromDate={reservationRequest.fromDate}
                toDate={reservationRequest.toDate}
                roomName={reservationRequest.roomName}
                numberOfGuests={reservationRequest.numberOfGuests}
                location={reservationRequest.location}
              />
            );
          })}
        </div>
      )}

    {reservationRequests.length === 0 && (
        <div className="rooms-wrapper">
          You currently have no active reservation requests
        </div>
      )}

    </>
  )
}
