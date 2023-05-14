import { element } from "prop-types";
import React, { useEffect, useState } from "react";
import { createAvailability } from "../../service/RoomService";
import { getIdFromLocalStorage } from "../../utils/LocalStorageService";

export const CreateAvailability = (roomId) => {
  const [showing, setShowing] = useState(false);
  const [from, setFrom] = useState();
  const [to, setTo] = useState();
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
                createAvailability({
                  roomId: roomId.roomId,
                  from: from,
                  to: to,
                  hostId: parseInt(getIdFromLocalStorage()),
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
