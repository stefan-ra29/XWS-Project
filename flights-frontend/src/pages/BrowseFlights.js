import SingleFlightSearchResult from '../components/flights/SingleFlightSearchResult'
import {useEffect, useState} from 'react'
import { searchFlights, getAvailablePlaces } from '../services/FlightService'
import './styles/BrowseFlights.css'
import FlightsSearchForm from '../components/flights/FLightsSearchForm'


export default function BrowseFlights() {

    const [flights, setFlights] = useState([])
    const [numberOfTickets, setNumberOfTickets] = useState(0)
    const [departures, setDepartures] = useState([]);
    const [destinations, setDestinations] = useState([]);

    useEffect(() => {
        getAvailablePlaces(setDepartures, setDestinations);
    }, [])

    const [searchQuery, setSearchQuery] = useState({
        departureDate: "",
        arrivalDate: "",
        departurePlace: "",
        destination: "",
        numberOfTickets: 0
    });

    function handleInputChange(event) {
        const { name, value } = event.target;
        setSearchQuery(prevState => ({
          ...prevState,
          [name]: value
        }));
    }

    const handleSubmit = (event) => {
        event.preventDefault();
        searchFlights(searchQuery, setFlights, setNumberOfTickets);
    }


  return (
    <>
        <h2 className='heading'>Search flights and buy your tickets!</h2>

        <div>
            <p className='searchDescription'>What kind of flight are you looking for?</p>

            <FlightsSearchForm handleSubmit={handleSubmit} searchQuery={searchQuery} handleInputChange={handleInputChange} departures={departures} destinations={destinations} />

            {flights.length !== 0 &&
                <div className='flights-wrapper'>
                    {flights.map(flight => {
                        return <SingleFlightSearchResult
                            key={flight.id}
                            departureDate={flight.departureDateTime} 
                            arrivalDate={flight.arrivalDateTime}
                            pricePerTicket={flight.pricePerTicket}
                            numberOfTickets={numberOfTickets}
                            departurePlace={flight.departure}
                            destination={flight.destination}
                        />
                    })}
                </div>
            }

            {flights.length === 0 &&
                <div className='flights-wrapper'>
                    <p className='no-result-text'>No flights match your search criteria :( Try something else</p>
                </div>
            }
             
        </div>
    </>
  )
}
