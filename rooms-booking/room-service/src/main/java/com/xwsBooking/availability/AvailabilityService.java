package com.xwsBooking.availability;

import com.xwsBooking.room.AvailabilityRequest;
import com.xwsBooking.room.AvailabilityResponse;
import com.xwsBooking.room.RoomAvailabilitiesRequest;
import com.xwsBooking.room.RoomAvailabilitiesResponse;

public interface AvailabilityService {
    AvailabilityResponse create(AvailabilityRequest dto);
    RoomAvailabilitiesResponse getAllFromRoom(RoomAvailabilitiesRequest request);
}
