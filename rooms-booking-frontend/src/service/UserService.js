import { toast } from "react-toastify";
import { getAxios } from "../utils/AxiosWrapper";

const apiURL = "http://localhost:8085/api/user/";

export function register(user) {
  console.log(user);
  getAxios()
    .post(apiURL, user)
    .then((response) => {
      toast.success("You have successfully registered!", {
        position: "top-right",
      });
      console.log(response);
      window.location.replace("/");
    })
    .catch((error) => {
      console.log(error);
      toast.error("Something went wrong, please try again later.");
    });
}
