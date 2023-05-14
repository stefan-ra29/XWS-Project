import React, { useState } from "react";
import { createAvailability } from "../../service/RoomService";
import { getIdFromLocalStorage } from "../../utils/LocalStorageService";

export const Availability = ({ av, roomId }) => {
  const [changing, setChanging] = useState(false);
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
    <div className="bg-white p-5" key={av.id}>
      <div className="flex justify-between">
        <div>
          <div className="flex gap-4">
            <div className="text-2xl font-bold">From:</div>
            {!changing && <div className="text-2xl font-thin">{av.from}</div>}
            {changing && (
              <input
                type="date"
                name="dateFrom"
                id="dateFrom"
                min={getValidFromDate()}
                onChange={(e) => setFrom(e.target.value)}
              ></input>
            )}
          </div>
          <div className="flex gap-4">
            <div className="text-2xl font-bold">To:</div>
            {!changing && <div className="text-2xl font-thin">{av.to}</div>}
            {changing && (
              <input
                type="date"
                name="dateFrom"
                id="dateFrom"
                min={getValidToDate()}
                onChange={(e) => setTo(e.target.value)}
              ></input>
            )}
          </div>
        </div>
        {!changing && (
          <div
            className="self-center bg-cyan-100 p-5 rounded-lg cursor-pointer"
            onClick={() => setChanging(!changing)}
          >
            Change
          </div>
        )}
        {changing && (
          <div className="flex gap-4">
            <div
              className="self-center bg-cyan-100 p-5 rounded-lg cursor-pointer"
              onClick={() => {
                console.log(roomId);
                createAvailability({
                  id: av.id,
                  roomId: roomId,
                  from: from,
                  to: to,
                  hostId: parseInt(getIdFromLocalStorage()),
                });
              }}
            >
              Change
            </div>
            <div
              className="self-center bg-red-400 p-5 rounded-lg cursor-pointer"
              onClick={() => setChanging(!changing)}
            >
              Cancel
            </div>
          </div>
        )}
      </div>
    </div>
  );
};
