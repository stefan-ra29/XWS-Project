package com.xwsBooking.price;

import com.xwsBooking.room.PriceRequest;
import com.xwsBooking.room.PriceResponse;
import com.xwsBooking.room.RoomPricesRequest;
import com.xwsBooking.room.RoomPricesResponse;

public interface PriceService {
    PriceResponse create(PriceRequest request);
    RoomPricesResponse getAllRoomPrices(RoomPricesRequest request);
}
