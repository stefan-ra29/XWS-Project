import { toast } from "react-toastify";
import { getAxios } from "../utils/AxiosWrapper";
import { getIdFromLocalStorage } from "../utils/LocalStorageService";

const apiURL = "http://localhost:8086/api/flight_ticket_purchase/";

export function buyTickets(
  flightId,
  numberOfTickets,
  totalPrice,
  onBuyResetHandler
) {
  getAxios()
    .post(apiURL + "buy", {
      flightId: flightId,
      numberOfTickets: numberOfTickets,
      totalPrice: totalPrice,
      customerId: getIdFromLocalStorage(),
    })
    .then((response) => {
      console.log("ticket bought");
      toast.success("You have successfully bought your tickets!", {
        position: "top-right",
      });
      onBuyResetHandler();
    })
    .catch((error) => {
      console.log(error);
    });
}
export function getUsersPurchasedTickets(setPurchasedTickets) {
  getAxios()
    .get(apiURL + "getByUser", { params: { userId: getIdFromLocalStorage() } })
    .then((response) => {
      console.log("i got tickets");
      console.log(response.data);
      setPurchasedTickets(response.data);
    })
    .catch((error) => {
      console.log(error);
    });
}
