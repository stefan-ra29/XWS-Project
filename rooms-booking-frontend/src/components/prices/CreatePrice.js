import React, { useState } from "react";
import { createPrice } from "../../service/RoomService";
import { getIdFromLocalStorage } from "../../utils/LocalStorageService";

export const CreatePrice = (roomId) => {
  const [showing, setShowing] = useState(false);
  const [from, setFrom] = useState();
  const [to, setTo] = useState();
  const [price, setPrice] = useState();
  const [type, setType] = useState();

  const getValidFromDate = () => {
    const today = new Date();
    const dd = String(today.getDate()).padStart(2, "0");
    const mm = String(today.getMonth() + 1).padStart(2, "0"); //January is 0!
    const yyyy = today.getFullYear();
    return yyyy + "-" + mm + "-" + dd;
  };

  const getValidToDate = () => {
    const today = new Date();
    const dd = String(today.getDate() + 1).padStart(2, "0");
    const mm = String(today.getMonth() + 1).padStart(2, "0"); //January is 0!
    const yyyy = today.getFullYear();
    return yyyy + "-" + mm + "-" + dd;
  };

  return (
    <div className="flex justify-between">
      <div>
        {showing && (
          <div className="p-5">
            <div className="flex text-lg gap-5">
              <div>From: </div>
              <input
                type="date"
                name="dateFrom"
                id="dateFrom"
                min={getValidFromDate()}
                onChange={(element) => setFrom(element.target.value)}
              ></input>
            </div>
            <div className="flex text-lg gap-5">
              <div>To: </div>
              <input
                type="date"
                name="dateFrom"
                id="dateFrom"
                min={getValidToDate()}
                onChange={(element) => setTo(element.target.value)}
              ></input>
            </div>
            <div className="flex text-lg gap-5">
              <div>Price: </div>
              <input
                type="number"
                name="dateFrom"
                min={1}
                onChange={(element) => setPrice(element.target.value)}
              ></input>
            </div>
            <div className="flex text-lg gap-5">
              <div>Type: </div>
              <select
                value={type}
                onChange={(element) => setType(element.target.value)}
              >
                <option value={0}>By guest</option>
                <option value={1}>By room unit</option>
              </select>
            </div>
          </div>
        )}
      </div>
      <div className="self-center m-5">
        {!showing && (
          <div
            className="self-center bg-cyan-500 p-5 rounded-lg cursor-pointer"
            onClick={() => setShowing(!showing)}
          >
            Create new
          </div>
        )}
        {showing && (
          <div className="flex gap-5 ">
            <div
              className="bg-cyan-500 p-5 rounded-lg cursor-pointer"
              onClick={() => {
                createPrice({
                  roomId: roomId.roomId,
                  from: from,
                  to: to,
                  hostId: parseInt(getIdFromLocalStorage()),
                  price: parseInt(price),
                  type: parseInt(type),
                });
              }}
            >
              Create
            </div>
            <div
              className="bg-red-400 p-5 rounded-lg cursor-pointer"
              onClick={() => setShowing(!showing)}
            >
              Cancel
            </div>
          </div>
        )}
      </div>
    </div>
  );
};
