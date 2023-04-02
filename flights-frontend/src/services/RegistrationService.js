import { toast } from "react-toastify";
import { getAxios } from "../utils/AxiosWrapper";

const apiURL = "http://localhost:8086/api/user/";

export function register(dto) {
  getAxios()
    .post(apiURL + "register", dto)
    .then((response) => {
      toast.success("You have successfully registered your account!", {
        position: "top-right",
      });
      window.location.replace("/");
    })
    .catch((error) => {
      toast.error(error.response.data.message);
    });
}
