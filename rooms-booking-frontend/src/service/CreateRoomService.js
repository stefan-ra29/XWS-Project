import { toast } from "react-toastify";
import { getAxios } from "../utils/AxiosWrapper";
import axios from "axios";

const apiURL = "http://localhost:8085/api/rooms";

export function createRoom(room) {
  const formData = new FormData();
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
