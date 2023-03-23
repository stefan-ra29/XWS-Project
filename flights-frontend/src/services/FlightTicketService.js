import axios from "axios";

const apiURL = "http://localhost:8086/api/flight_ticket_purchase/";

export function buyTickets(flightId, numberOfTickets, totalPrice) {
  axios
    .post(apiURL + "buy", {
      flightId: flightId,
      numberOfTickets: numberOfTickets,
      totalPrice: totalPrice,
    })
    .then((response) => {
      console.log("ticket bought");
    })
    .catch((error) => {
      console.log(error);
    });
}
