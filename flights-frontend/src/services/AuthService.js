import { toast } from "react-toastify";
import { getAxios } from "../utils/AxiosWrapper";
import jwt_decode from "jwt-decode";

const apiURL = "http://localhost:8086/api/auth";

export function login(dto) {
  getAxios()
    .post(apiURL, dto)
    .then((response) => {
      localStorage.setItem("token", response.data);
      console.log(jwt_decode(response.data));
      localStorage.setItem("role", jwt_decode(response.data).role);
      localStorage.setItem("id", jwt_decode(response.data).id);
      window.location.replace("http://localhost:3000/");
      toast.success("Successfully logged in");
    })
    .catch((error) => {
      toast.error(error.response.data.message);
    });
}
