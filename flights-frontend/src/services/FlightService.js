import { getAxios } from "../utils/AxiosWrapper";
import { toast } from "react-toastify";

const apiURL = "http://localhost:8086/api/flight/";

export function getAllFlights(setFlights) {
  getAxios()
    .get(apiURL + "getAll")
    .then((response) => {
      setFlights(response.data);
    })
    .catch((error) => {
      console.log(error);
    });
}

export function searchFlights(searchQuery, setFlights, setNumberOfTickets) {
  getAxios()
    .post(apiURL + "search", searchQuery)
    .then((response) => {
      setFlights(response.data);
      setNumberOfTickets(searchQuery.numberOfTickets);
    })
    .catch((error) => {
      console.log(error);
    });
}

export function getAvailablePlaces(setDepartures, setDestinations) {
  getAxios()
    .get(apiURL + "available-places")
    .then((response) => {
      setDepartures(response.data.departures);
      setDestinations(response.data.destinations);
    })
    .catch((error) => {
      console.log(error);
    });
} 

export function saveFlight(newFlight) {
  getAxios()
    .post(apiURL + "save", newFlight)
    .then((response) => {
      console.log("flight saved");
    })
    .catch((error) => {
      console.log(error);
    });
}

export function deleteFlight(id) {
  getAxios()
    .delete(apiURL + "delete?id=" + id)
    .then((response) => {
        toast.success("You have successfully deleted flight!")
    })
    .catch((error) => {
      console.log(error);
    });
}
