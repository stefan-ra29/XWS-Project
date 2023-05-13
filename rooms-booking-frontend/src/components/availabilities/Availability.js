import React, { useState } from "react";

export const Availability = ({ av }) => {
  const [changing, setChanging] = useState(false);

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
              ></input>
            )}
          </div>
        </div>
        <div
          className="self-center bg-cyan-100 p-5 rounded-lg cursor-pointer"
          onClick={() => setChanging(!changing)}
        >
          Change
        </div>
      </div>
    </div>
  );
};
