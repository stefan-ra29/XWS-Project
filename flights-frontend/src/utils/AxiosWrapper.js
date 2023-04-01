import axios from "axios";

const defaultHeaders = {
  accept: "application/json",
  "Content-Type": "application/json",
};

export function getAxios() {
  const token = localStorage.getItem("token");
  if (token == null) {
    return axios.create({
      baseURL: "http://localhost:8000",
      headers: {
        ...defaultHeaders,
      },
    });
  }
  return axios.create({
    baseURL: "http://localhost:8000",
    headers: {
      ...defaultHeaders,
      Authorization: `Bearer ${token}`,
    },
  });
}
