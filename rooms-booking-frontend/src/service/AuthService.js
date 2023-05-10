import { toast } from "react-toastify";
import { getAxios } from "../utils/AxiosWrapper";
import jwt_decode from "jwt-decode";

const apiURL = "http://localhost:8085/api/login";

export function login(credentials, navigate) {
  getAxios()
    .post(apiURL, credentials)
    .then((response) => {
      localStorage.setItem("token", response.data);
      console.log(jwt_decode(response.data));
      localStorage.setItem("role", jwt_decode(response.data).role);
      localStorage.setItem("id", jwt_decode(response.data).id);
      localStorage.setItem("email", jwt_decode(response.data).email);
      console.log(localStorage.getItem("role"));
      navigate("/");
      toast.success("Successfully logged in");
    })
    .catch((error) => {
      toast.error(error.response.data.message);
    });
}
