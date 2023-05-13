import { toast } from "react-toastify";
import { getAxios } from "../utils/AxiosWrapper";

const apiURL = "http://localhost:8085/api/user/";

export function register(user, navigate) {
  getAxios()
    .post(apiURL, user)
    .then((response) => {
      toast.success("You have successfully registered!", {
        position: "top-right",
      });
      navigate("/");
    })
    .catch((error) => {
      console.log(error);
      if (error.response.status === 406) {
        var validationErrors = new Map();
        Object.keys(error.response.data).map(function (key) {
          return validationErrors.set(error.response.data[key], key);
        });
        console.log(validationErrors);

        validationErrors.forEach((key, value, map) => {
          return toast.error(value);
        });
      } else {
        toast.error("Something went wrong, please try again later.");
      }
    });
}

export function getUser(id) {
  getAxios()
    .get(apiURL, id)
    .then((response) => {
      return response.data;
    })
    .catch((error) => {
      console.log(error);
    });
}
