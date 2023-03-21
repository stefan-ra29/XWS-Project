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