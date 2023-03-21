import axios from 'axios'

export function getAllFlights(setFlights) {
    axios.get('http://localhost:8080/api/flight/getAll')
        .then(response => {
            setFlights(response.data)        
        })
        .catch(error => {
            console.log(error);
        })
}

export function searchFlights(searchQuery, setFlights, setNumberOfTickets) {
    axios.post('http://localhost:8080/api/flight/search', searchQuery)
        .then(response => {
            setFlights(response.data)
            setNumberOfTickets(searchQuery.numberOfTickets)
        })
        .catch(error => {
            console.log(error);
        });
}

export function getAvailablePlaces(setDepartures, setDestinations) {
    axios.get('http://localhost:8080/api/flight/available-places')
        .then(response => {
            setDepartures(response.data.departures)      
            setDestinations(response.data.destinations)        
        })
        .catch(error => {
            console.log(error);
        })
}