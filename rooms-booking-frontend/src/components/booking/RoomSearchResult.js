import React, { useState } from 'react'
import 'react-photo-view/dist/react-photo-view.css';
import './RoomSearchResult.css'
import { PhotoProvider, PhotoView } from 'react-photo-view';
import { sendReservationRequest } from '../../service/BookingService';

export default function RoomSearchResult(props) {

    const [showAllImages, setShowAllImages] = useState(false);
    const [allImages, setAllImages] = useState(props.images)

    function handleImageViewing() {
        setShowAllImages(!showAllImages);
    }

    function handleSendReservationRequest() {
        const request = {
            roomId: props.roomId,
            fromDate: props.fromDate,
            toDate: props.toDate,
            guestId: 1007,
            numberOfGuests: props.numberOfGuests
        }

        sendReservationRequest(request);
    }

  return (
    <>
        <div className='room-wrapper'>
            <div className='info-wrapper'>
                <div className='room-name'>
                    Room: {props.name}
                </div>

                <div className='room-location'>
                    Location: {props.location}
                </div>

                <div className='commodity'>
                    Has AC: {props.hasAC ? <div className='green'>Yes</div> : <div className='red'>No</div>}
                </div>
                
                <div className='commodity'>
                    Has Wifi: {props.hasWifi ? <div className='green'>Yes</div> : <div className='red'>No</div>}
                </div>

                <div className='commodity'>
                    Has Kitchen: {props.hasKitchen ? <div className='green'>Yes</div> : <div className='red'>No</div>}
                </div>

                <div className='commodity'>
                    Has free parking: {props.hasFreeParking ? <div className='green'>Yes</div> : <div className='red'>No</div>}
                </div>

                <div>
                    Minimum number of guests for room: {props.minGuests}
                </div>

                <div>
                    Maximum number of guests for room: {props.maxGuests}
                </div>

                <div>
                    Total price: ${props.totalPrice} 
                </div>

                <div>
                    Price per night: ${props.pricePerDay}
                </div>
            </div>
            
            <div className='image-wrapper'>
            
                <img onClick={handleImageViewing} src={props.images[0]} alt='slika'></img>
                

                <button onClick={handleSendReservationRequest} className='reservationRequestButton'>Send reservation request for selected date range and number of guests</button>
            </div>
            
        </div>
        
        {showAllImages &&
        <PhotoProvider>
            <div className="allImages">
                {allImages.map((item, index) => (
                <PhotoView key={index} src={item}>
                    <img src={item} alt="" />
                </PhotoView>
                ))}
            </div>
        </PhotoProvider>
        }
        
        
        
    </>
  )
}
