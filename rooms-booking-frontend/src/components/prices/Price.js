import React, { useState } from "react";

export const Price = ({ price }) => {
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
    <div className="bg-white p-5" key={price.id}>
      <div className="flex justify-between">
        <div>
          <div className="flex gap-4">
            <div className="text-2xl font-bold">From:</div>
            {!changing && (
              <div className="text-2xl font-thin">{price.from}</div>
            )}
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
            {!changing && <div className="text-2xl font-thin">{price.to}</div>}
            {changing && (
              <input
                type="date"
                name="dateFrom"
                id="dateFrom"
                min={getValidToDate()}
              ></input>
            )}
          </div>
          <div className="flex gap-4">
            <div className="text-2xl font-bold">Price: </div>
            <div className="text-2xl font-thin">{price.price}</div>
          </div>
          <div className="flex gap-4">
            <div className="text-2xl font-bold">Type: </div>
            <div className="text-2xl font-thin">
              {price.type == 0 ? "By guest" : "By room unit"}
            </div>
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
