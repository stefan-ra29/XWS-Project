import React, { useEffect, useState } from "react";
import { getHostRooms } from "../service/RoomService";
import { HostRoom } from "../components/host-rooms/HostRoom";

export const HostRooms = () => {
  const [rooms, setRooms] = useState();

  useEffect(() => {
    getHostRooms(setRooms);
  }, []);

  return (
    <div className="m-auto w-[800px] mb-10">
      {rooms && rooms.map((room) => <HostRoom room={room} />)}
    </div>
  );
};
