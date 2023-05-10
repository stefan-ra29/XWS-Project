import { toast } from "react-toastify";
import { getAxios } from "../utils/AxiosWrapper";

const apiURL = "http://localhost:8085/api/user/";

export function register(user, setIsChosen, setIsHost, navigate) {
  getAxios()
    .post(apiURL, user)
    .then((response) => {
      toast.success("You have successfully registered!", {
        position: "top-right",
      });
      setIsChosen(false);
      setIsHost(false);
      navigate("/");
    })
    .catch((error) => {
      console.log(error);
      toast.error("Something went wrong, please try again later.");
      setIsChosen(false);
      setIsHost(false);
    });
}
