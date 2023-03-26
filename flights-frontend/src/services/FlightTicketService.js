import axios from "axios";
import { toast } from 'react-toastify';

const apiURL = "http://localhost:8086/api/flight_ticket_purchase/";

export function buyTickets(flightId, numberOfTickets, totalPrice, onBuyResetHandler) {
  axios
    .post(apiURL + "buy", {
      flightId: flightId,
      numberOfTickets: numberOfTickets,
      totalPrice: totalPrice,
    })
    .then((response) => {
      console.log("ticket bought");
      toast.success('You have successfully bought your tickets!', {
          position: "top-right",
       });
      onBuyResetHandler();
    })
    .catch((error) => {
      console.log(error);
    });
}
