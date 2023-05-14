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
      localStorage.setItem("password", credentials.password);
      toast.success("Successfully logged in");
      window.setTimeout(
        () => window.location.replace("http://localhost:3000/"),
        500
      );
    })
    .catch((error) => {
      toast.error(error.response.data.message);
    });
}
