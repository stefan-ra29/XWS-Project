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

export function getUser(id, setUser, setAddress) {
  getAxios()
    .get(apiURL + `${id}`)
    .then((response) => {
      response.data.password = localStorage.getItem("password");
      setUser(response.data);
      setAddress(response.data.address);
    })
    .catch((error) => {
      console.log(error);
    });
}
export function deleteAccount() {
  let userId = localStorage.getItem("id");
  getAxios()
    .delete(apiURL + userId)
    .then((response) => {
      if (response.data === "Account deleted successfully") {
        localStorage.clear();
        toast.info(response.data);
        window.setTimeout(
          () => window.location.replace("http://localhost:3000/"),
          3000
        );
      } else {
        toast.info(response.data);
      }
    })
    .catch((error) => {
      console.log(error);
      toast.error(error.message);
    });
}

export function changeUserInfo(
  user,
  setIsChangeActivated,
  setIsDeleteButtonDisabled,
  navigate
) {
  getAxios()
    .post(apiURL + "change", user)
    .then((response) => {
      setIsChangeActivated(false);
      setIsDeleteButtonDisabled(false);
      toast.success(
        "You have successfully changed information about your account! Please login again!",
        {
          position: "top-right",
        }
      );
      localStorage.clear();
      window.setTimeout(
        () => window.location.replace("http://localhost:3000/"),
        1000
      );
    })
    .catch((error) => {
      console.log(error);
      if (error.response.status === 406) {
        var validationErrors = new Map();
        Object.keys(error.response.data).map(function (key) {
          return validationErrors.set(error.response.data[key], key);
        });
        validationErrors.forEach((key, value, map) => {
          return toast.error(value);
        });
      } else if (error.response.status === 403) {
        toast.error(error.response.data);
      } else {
        toast.error("Something went wrong, please try again later.");
      }
    });
}
