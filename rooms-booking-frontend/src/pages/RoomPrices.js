import { getRoomPrices } from "../service/RoomService";
import React, { useEffect, useState } from "react";
import { useParams } from "react-router";
import { CreateAvailability } from "../components/availabilities/CreateAvailability";
import { Price } from "../components/prices/Price";
import { CreatePrice } from "../components/prices/CreatePrice";

export const RoomPrices = () => {
  const { id } = useParams();
  const [prices, setPrices] = useState();
  useEffect(() => {
    getRoomPrices(id, setPrices);
  }, []);

  return (
    <div className="m-auto w-[800px] my-10">
      <div
        className="bg-cyan-100 p-5 cursor-pointer w-fit rounded-lg mb-5"
        onClick={() => window.location.replace("/my-rooms")}
      >
        Back
      </div>
      {prices &&
        prices.map((price) => (
          <div key={price.id}>
            <Price pr={price} roomId={id} key={price.id} />
          </div>
        ))}
      <CreatePrice roomId={id} />
    </div>
  );
};
