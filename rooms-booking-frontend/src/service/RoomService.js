import { toast } from "react-toastify";
import { getAxios } from "../utils/AxiosWrapper";
import { getIdFromLocalStorage } from "../utils/LocalStorageService";

const apiURL = "http://localhost:8085/api/rooms";

export function createRoom(room) {
  const formData = new FormData();
  room.hostId = getIdFromLocalStorage();
  Object.keys(room).forEach((key) => formData.append(key, room[key]));
  formData.delete("files");
  for (let i = 0; i < room.files.length; i++) {
    formData.append("files", room.files[i]);
  }
  getAxios()
    .post(apiURL, formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    })
    .then((response) => {
      toast.success("You have successfully created room!", {
        position: "top-right",
      });
      console.log(response);
      //window.location.replace("/");
    })
    .catch((error) => {
      toast.error("Error while creating a room");
      console.log(error);
    });
}

export function getHostRooms(setRooms) {
  getAxios()
    .get("http://localhost:8085/api/rooms/host/" + getIdFromLocalStorage())
    .then((response) => {
      setRooms(response.data.rooms);
      console.log(response.data.rooms);
    })
    .catch((error) => {
      console.log(error);
      toast.error(error.message);
    });
}

export function getRoomAvailabilities(roomId, setAvailabilities) {
  getAxios()
    .get("http://localhost:8085/api/rooms/" + roomId + "/availabilities")
    .then((response) => {
      setAvailabilities(response.data.availabilities);
      console.log(response.data.availabilities);
    })
    .catch((error) => {
      console.log(error);
      toast.error(error.message);
    });
}

export function getRoomPrices(roomId, setPrices) {
  getAxios()
    .get("http://localhost:8085/api/rooms/" + roomId + "/prices")
    .then((response) => {
      setPrices(response.data.prices);
      console.log(response.data.prices);
    })
    .catch((error) => {
      console.log(error);
      toast.error(error.message);
    });
}

export function createAvailability(availability) {
  getAxios()
    .post("http://localhost:8085/api/rooms/create-availability", availability)
    .then((response) => {
      toast.success("You have successfully created availability!", {
        position: "top-right",
      });
      console.log(response);
      window.setTimeout(() => window.location.reload(), 5000);
    })
    .catch((error) => {
      toast.error(error.response.data.message);
    });
}

export function createPrice(price) {
  getAxios()
    .post("http://localhost:8085/api/rooms/create-price", price)
    .then((response) => {
      toast.success("You have successfully created price!", {
        position: "top-right",
      });
      console.log(response);
      window.setTimeout(() => window.location.reload(), 5000);
    })
    .catch((error) => {
      toast.error(error.response.data.message);
    });
}
