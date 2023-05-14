import React, { useEffect, useState } from "react";
import { getRoomAvailabilities } from "../service/RoomService";
import { useParams } from "react-router";
import { CreateAvailability } from "../components/availabilities/CreateAvailability";
import { Availability } from "../components/availabilities/Availability";

export const RoomAvailabilities = () => {
  const { id } = useParams();
  const [availabilities, setAvailabilities] = useState();
  useEffect(() => {
    getRoomAvailabilities(id, setAvailabilities);
  }, []);

  return (
    <div className="m-auto w-[800px] my-10">
      <div
        className="bg-cyan-100 p-5 cursor-pointer w-fit rounded-lg mb-5"
        onClick={() => window.location.replace("/my-rooms")}
      >
        Back
      </div>
      {availabilities &&
        availabilities.map((av) => (
          <div key={av.id}>
            <Availability av={av} roomId={id} />{" "}
          </div>
        ))}
      <CreateAvailability roomId={id} />
    </div>
  );
};
