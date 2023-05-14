import React, { useState } from "react";
import { createPrice } from "../../service/RoomService";
import { getIdFromLocalStorage } from "../../utils/LocalStorageService";

export const Price = ({ pr, roomId }) => {
  const [changing, setChanging] = useState(false);
  const [from, setFrom] = useState(pr.from);
  const [to, setTo] = useState(pr.to);
  const [price, setPrice] = useState(pr.price);
  const [type, setType] = useState(pr.type);

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
    pr && (
      <div className="bg-white p-5" key={pr.id}>
        <div className="flex justify-between">
          <div>
            <div className="flex gap-4">
              <div className="text-2xl font-bold">From:</div>
              {!changing && <div className="text-2xl font-thin">{pr.from}</div>}
              {changing && (
                <input
                  type="date"
                  name="dateFrom"
                  id="dateFrom"
                  value={from}
                  min={getValidFromDate()}
                  onChange={(e) => setFrom(e.target.value)}
                ></input>
              )}
            </div>
            <div className="flex gap-4">
              <div className="text-2xl font-bold">To:</div>
              {!changing && <div className="text-2xl font-thin">{pr.to}</div>}
              {changing && (
                <input
                  type="date"
                  name="dateFrom"
                  id="dateFrom"
                  min={getValidToDate()}
                  value={to}
                  onChange={(e) => setTo(e.target.value)}
                ></input>
              )}
            </div>
            <div className="flex gap-4">
              <div className="text-2xl font-bold">Price: </div>
              {!changing && (
                <div className="text-2xl font-thin">{pr.price}</div>
              )}
              {changing && (
                <input
                  type="number"
                  className="text-2xl font-thin"
                  value={price}
                  onChange={(e) => setPrice(e.target.value)}
                />
              )}
            </div>
            <div className="flex gap-4">
              <div className="text-2xl font-bold">Type: </div>
              {!changing && (
                <div className="text-2xl font-thin">
                  {pr.type == 0 ? "By guest" : "By room unit"}
                </div>
              )}
              {changing && (
                <select
                  value={type}
                  onChange={(element) => setType(element.target.value)}
                >
                  <option value={0} selected={pr.type == 0}>
                    By guest
                  </option>
                  <option value={1} selected={pr.type == 1}>
                    By room unit
                  </option>
                </select>
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
                  createPrice({
                    id: pr.id,
                    roomId: parseInt(roomId),
                    from: from,
                    to: to,
                    hostId: parseInt(getIdFromLocalStorage()),
                    price: parseInt(price),
                    type: parseInt(type),
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
    )
  );
};
