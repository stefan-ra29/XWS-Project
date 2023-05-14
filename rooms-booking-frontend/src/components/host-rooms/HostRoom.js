import React, { useState } from "react";
import { PhotoProvider, PhotoView } from "react-photo-view";

export const HostRoom = ({ room }) => {
  const [showAllImages, setShowAllImages] = useState(false);
  return (
    room && (
      <div className="bg-white mt-5 p-5" key={room.id}>
        <div className="text-center text-2xl font-bold">{room.name}</div>
        <div className="flex justify-around mt-5">
          <div>
            <div className="">{room.location}</div>
            <div className="font-thin">{room.wifi === true && "WIFI"}</div>
            <div className="font-thin">
              {room.kitchen === true && "Kitchen included"}
            </div>
            <div className="font-thin">
              {room.airConditioning === true && "AC included"}
            </div>
            <div className="font-thin">
              {room.freeParking === true && "Free parking"}
            </div>
          </div>
          <div>
            <img src={room.images[0]} width={200} alt={room.id + "image"} />

            <div className="">
              <div
                onClick={() => setShowAllImages(!showAllImages)}
                className="bg-cyan-100 rounded-lg p-2 text-center m-2 cursor-pointer"
              >
                {!showAllImages && "Show other images"}
                {showAllImages && "Close"}
              </div>
              <a
                href={"/my-rooms/" + room.id + "/availabilities"}
                className="bg-cyan-100 rounded-lg p-2 text-center m-2 cursor-pointer items-center"
              >
                Availabilities
              </a>
              <a
                href={"/my-rooms/" + room.id + "/prices"}
                className="bg-cyan-100 rounded-lg p-2 text-center m-2 cursor-pointer"
              >
                Prices
              </a>
            </div>
          </div>
        </div>
        {showAllImages && (
          <PhotoProvider>
            <div className="allImages">
              {room.images.map((item, index) => (
                <PhotoView key={index} src={item}>
                  <img src={item} alt="" />
                </PhotoView>
              ))}
            </div>
          </PhotoProvider>
        )}
      </div>
    )
  );
};
