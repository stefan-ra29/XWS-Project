import { toast } from "react-toastify";
import { getAxios } from "../utils/AxiosWrapper";

const apiURL = "http://localhost:8086/api/user/";

export function register(dto) {
  getAxios()
    .post(apiURL + "register", dto)
    .catch((error) => {
      toast.error(error.response.data.message);
    });
}
