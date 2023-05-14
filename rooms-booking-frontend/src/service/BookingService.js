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

export function getAvailableLocations(setAvailableLocations) {
  getAxios()
  .get("http://localhost:8085/api/reservations/available-locations")
  .then((response) => {
    setAvailableLocations(response.data);
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

export function getReservationRequestsByGuest(setReservationRequests) {
  let gouestId = localStorage.getItem("id");

  getAxios()
    .get("http://localhost:8085/api/reservations/reservation-requests/" + gouestId)
    .then((response) => {
      setReservationRequests(response.data)
    })
    .catch((error) => {
      console.log(error);
      toast.error(error.message);
    });
}

export function deleteGuestReservationRequest(requestId) {
  getAxios()
    .delete("http://localhost:8085/api/reservations/reservation-request/" + requestId)
    .then((response) => {
      window.location.reload();
      toast.success('You successfully canceled your reservation request!')
    })
    .catch((error) => {
      console.log(error);
      toast.error(error.message);
    });
}

export function getApprovedReservationsByGuest(setApprovedReservation) {
  let guestId = localStorage.getItem("id");

  getAxios()
    .get("http://localhost:8085/api/reservations/approved-reservations/" + guestId)
    .then((response) => {
      setApprovedReservation(response.data)
    })
    .catch((error) => {
      console.log(error);
      toast.error(error.message);
    });
}

export function cancelApprovedReservation(reservationId) {
  getAxios()
    .get("http://localhost:8085/api/reservations/cancel-reservation/" + reservationId)
    .then((response) => {
      window.location.reload();
      toast.success('You successfully canceled your reservation!')
    })
    .catch((error) => {
      console.log(error);
      toast.error(error.message);
    });
}

export function getReservationRequestsByHost(setReservationRequests) {
  let hostId = localStorage.getItem("id");

  getAxios()
    .get("http://localhost:8085/api/reservations/host-requests/" + hostId)
    .then((response) => {
      setReservationRequests(response.data)
    })
    .catch((error) => {
      console.log(error);
      toast.error(error.message);
    });
}

export function acceptReservationRequest(reservationId) {
  getAxios()
    .get("http://localhost:8085/api/reservations/approve-reservation/" + reservationId)
    .then((response) => {
      window.location.reload();
      toast.success('You successfully accepted reservation!')
    })
    .catch((error) => {
      console.log(error);
      toast.error(error.message);
    });
}

export function declineReservationRequest(reservationId) {
  getAxios()
    .delete("http://localhost:8085/api/reservations/decline-reservation/" + reservationId)
    .then((response) => {
      window.location.reload();
      toast.success('You successfully declined reservation!')
    })
    .catch((error) => {
      console.log(error);
      toast.error(error.message);
    });
}