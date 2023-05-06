package com.xwsBooking.availability;

import com.xwsBooking.room.AvailabilityRequest;
import com.xwsBooking.room.AvailabilityResponse;

public interface AvailabilityService {
    AvailabilityResponse create(AvailabilityRequest dto);
}
