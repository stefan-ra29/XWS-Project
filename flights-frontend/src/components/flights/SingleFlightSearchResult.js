import './SingleFlightSearchResult.css'

export default function SingleFlightSearchResult({departureDate, arrivalDate, pricePerTicket, numberOfTickets, departurePlace, destination}) {

    const totalPrice = parseInt(pricePerTicket)*parseInt(numberOfTickets);

  return (
    <div className='flight-wrapper'>
        <div className='info-section'>
            <p>Depart from: </p>
            <p>{departurePlace}</p>
        </div>
        <div className='info-section'>
            <p>On: </p>
            <p>{departureDate}</p>
        </div>
        <div className='info-section'>
            <p>Destination: </p>
            <p>{destination}</p>
        </div>
        <div className='info-section'>
            <p>On: </p>
            <p>{arrivalDate}</p>
        </div>
        <div className='info-section'>
            <p>Price per ticket: </p>
            <p>${pricePerTicket}</p>
        </div>
        <div className='info-section'>
            <p>Total price for {numberOfTickets} tickets: </p>
            <p>${totalPrice}</p>
        </div>
    </div>
  )
}
