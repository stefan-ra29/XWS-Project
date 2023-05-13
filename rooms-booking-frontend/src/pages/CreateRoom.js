import React, { useEffect } from "react";
import { useState } from "react";
import { createRoom } from "../service/CreateRoomService";

export const CreateRoom = () => {
  const [room, setRoom] = useState({
    maxNumberOfGuests: 1,
    minNumberOfGuests: 0,
  });
  useEffect(() => {
    console.log(room);
  }, [room]);

  return (
    <div className="bg-white w-[500px] m-auto rounded-lg p-8">
      <div className="flex justify-between">
        <div className="self-center">Name:</div>
        <input
          type="text"
          className="border border-gray-500 rounded-md"
          onChange={(event) => setRoom({ ...room, name: event.target.value })}
        />
      </div>
      <div className="flex justify-between mt-4">
        <div className="self-center">Location:</div>
        <input
          type="text"
          className="border border-gray-500 rounded-md"
          onChange={(event) =>
            setRoom({ ...room, location: event.target.value })
          }
        />
      </div>
      <div className="flex justify-between mt-4">
        <div className="self-center">Max guests:</div>
        <input
          type="number"
          id="maxNum"
          min={room.minNumberOfGuests + 1}
          className="border border-gray-500 rounded-md"
          onChange={(event) =>
            setRoom({ ...room, maxNumberOfGuests: event.target.valueAsNumber })
          }
        />
      </div>
      <div className="flex justify-between mt-4">
        <div className="self-center">Min guests:</div>
        <input
          type="number"
          max={room.maxNumberOfGuests - 1}
          min={0}
          className="border border-gray-500 rounded-md"
          onChange={(event) =>
            setRoom({ ...room, minNumberOfGuests: event.target.valueAsNumber })
          }
        />
      </div>
      <div className="flex justify-between mt-4">
        <div className="self-center">Free parking:</div>
        <input
          type="checkbox"
          className="border border-gray-500 rounded-md"
          onChange={(event) =>
            setRoom({ ...room, freeParking: event.target.checked })
          }
        />
      </div>
      <div className="flex justify-between  mt-4">
        <div className="self-center">Air conditioning:</div>
        <input
          type="checkbox"
          className="border border-gray-500 rounded-md"
          onChange={(event) =>
            setRoom({ ...room, airConditioning: event.target.checked })
          }
        />
      </div>
      <div className="flex justify-between  mt-4">
        <div className="self-center">Wi-fi:</div>
        <input
          type="checkbox"
          className="border border-gray-500 rounded-md"
          onChange={(event) => setRoom({ ...room, wifi: event.target.checked })}
        />
      </div>
      <div className="flex justify-between  mt-4">
        <div className="self-center">Kitchen included:</div>
        <input
          type="checkbox"
          className="border border-gray-500 rounded-md"
          onChange={(event) =>
            setRoom({ ...room, kitchen: event.target.checked })
          }
        />
      </div>
      <div className="flex justify-between  mt-4">
        <div className=" self-center">Images:</div>
        <input
          id="fileInput"
          type="file"
          multiple
          className="text-right"
          onChange={(event) => setRoom({ ...room, files: event.target.files })}
        />
      </div>
      <div className="flex justify-between">
        <div className="self-center"></div>
        <button
          className="border border-gray-500 rounded-md p-2 hover:bg-gray-500 hover:text-white"
          onClick={() => createRoom(room)}
        >
          Create
        </button>
      </div>
    </div>
  );
};
