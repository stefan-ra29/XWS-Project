import SingleFlightSearchResult from '../components/flights/SingleFlightSearchResult'
import {useEffect, useState} from 'react'
import { getAllFlights } from '../services/FlightService'
import './styles/BrowseFlights.css'


export default function BrowseFlights() {

    const [flights, setFlights] = useState([])

    useEffect(() => {
        getAllFlights(setFlights);
    }, [])

  return (
    <>
        <h2 className='heading'>Search flights and buy your tickets!</h2>

        <div>
            <p className='searchDescription'>What kind of flight are you looking for?</p>

            <form className='searchForm'>
                <div className='departureSection'>
                    <label htmlFor='departure'>Depart from</label>
                    <select name='departure' id='departure'>
                        <option value="Belgrade">Belgrade</option>
                        <option value="Zagreb">Zagreb</option>
                        <option value="London">London</option>
                    </select>

                    <label htmlFor='deartureDate'>On</label>
                    <input type="date" id='departureDate'/>
                </div>

                <div className='destinationSection'>
                    <label htmlFor='destination'>Destination</label>
                    <select name='destination' id='destination'>
                        <option value="Belgrade">Belgrade</option>
                        <option value="Zagreb">Zagreb</option>
                        <option value="London">London</option>
                    </select>

                    <label htmlFor='destinationDate'>On</label>
                    <input type="date" id='destinationDate'/>
                </div>

                <div className='ticketsSection'>
                    <label>Number of tickets</label>
                    <input className='ticketsInput' type='number' min='1' step='1' />

                    <button className='searchButton' type='submit'>Search!</button>
                </div>

                <div className='buttonWrapper'>
                    
                </div>

                
            </form>

            <div className='flights-wrapper'>
                {flights.map(flight => {
                    return <SingleFlightSearchResult
                        key={flight.id}
                        departureDate={flight.departureDateTime} 
                        arrivalDate={flight.arrivalDateTime}
                        pricePerTicket={flight.pricePerTicket}
                        numberOfTickets={3}
                        departurePlace={flight.departure}
                        destination={flight.destination}
                    />
                })}
            </div>
            
            
        </div>
    </>
  )
}
