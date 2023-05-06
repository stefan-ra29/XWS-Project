package com.xwsBooking.price;

import com.xwsBooking.room.PriceRequest;
import com.xwsBooking.room.PriceResponse;

public interface PriceService {
    PriceResponse create(PriceRequest request);
}
