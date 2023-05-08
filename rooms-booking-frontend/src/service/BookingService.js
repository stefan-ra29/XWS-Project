import { toast } from "react-toastify";
import { getAxios } from "../utils/AxiosWrapper";


export function searchRooms(searchQuery, setRooms) {
    getAxios()
    .post("http://localhost:8085/api/reservations/search", searchQuery)
    .then((response) => {
      setRooms(response.data)
      console.log(response.data)
    })
    .catch((error) => {
      console.log(error);
      toast.error(error.message);
    });
}


export function sendReservationRequest(reservationRequest) {
    getAxios()
    .post("http://localhost:8085/api/reservations/send-reservation-request", reservationRequest)
    .then((response) => {
      toast.success('You have sucessfully sent your reservation request!')
    })
    .catch((error) => {
      console.log(error);
      toast.error(error.message);
    });
}